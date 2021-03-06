/**
 * swagger-mg-ui是swagger-ui的一个前端实现，使用简单、解析速度快、走心的设计
 * 支持多项目同时展示，多种文档目录的展示方案，多种自定义配置，满足各种使用习惯。
 * 使用中您有任何的意见和建议都可到源码地址处反馈哦！
 * git地址：https://gitee.com/zyplayer/swagger-mg-ui
 */

// 参数说明对于的map全局对象
var definitionsDataMap = new Map();
// 依据目录树存储的map全局对象
var treePathDataMap = new Map();

var globalLoadingMessager;
// 服务器端存储能力是否开启
var serverStorage = false;
// 树的下表
var projectTreeIdIndex = 1;
// 文档url加载的下标
var projectLoadingIndex = 0;
// 请求到的文档列表
var documentJsonArr = [];
// 用户的配置对象
var userSettings = {};
// 默认用户的配置对象
var defaultUserSettings = {
	autoFillParam : 0,// 自动填充参数，0=否  1=智能填充  2=全部填充
	onlyUseLastParam : 0,// 是否仅使用上次请求参数
	showParamType : 1,// 是否展示字段的类型
	catalogShowType : 2,// 目录的展示方式，1=url分成一层一层的展示、2=整个url显示为一层展示
	treeShowType : 1,// 树形菜单展示方式，1=tree-angles、2=tree-menu、3=默认，4=tree-folders、5=tree-chevrons
	projects : [],// 所有的项目列表
	removedProjects : [],// 被移除的项目列表
	prevWNow : 360
};

var swaggerApiDocsArr = [];

var requestParamObj = {};
// 模板示例
var requestParamObjTemp = {
	taskId: {
		value: 1,// 参数值
		paramType: 'string',// 参数类型，string int boolean
		required: true,// 是否必填
		paramIn: 'body',// 参数位置，body query header
		paramDesc: '参数说明'
	}
};

/**
 * 网页加载完毕后的处理
 */
$(document).ready(function(){
	globalLoadingMessager = new $.zui.Messager({type: 'primary', close: false, time: 0}).show();
	showGlobalLoadingMessage('检查服务中，请稍候...', true);
	initUserSettings();
});

/**
 * 增加文档
 * @returns
 */
function addDocumentByUrl(fullUrl, isInSetting) {
	// 获取原始的swagger的json对象
	ajaxTemp(fullUrl, "get", "json", {}, function(json){
		console.log(json);
		if(isEmptyObject(json) || isEmpty(json.swagger)) {
			Toast.error("获取文档错误，请检查：" + fullUrl);
			return
		}
		// 拼接地址
		json.fullUrl = fullUrl;
		var v2Index = fullUrl.indexOf("/v2/api-docs");
		if(v2Index > 0) {
			var domainUrl = fullUrl.substring(0, v2Index + 1);
			json.domainUrl = domainUrl;
		}
		documentJsonArr.push(json);// 加到所有文档
		addHomePageDashboard(json, fullUrl);
		createDefinitionsMapByJson(json);
		/*if(userSettings.catalogShowType == 1) {
			createTreeViewByTree(json);// url分成一层一层的展示
		} else if(userSettings.catalogShowType == 2){
			
		} else {
			createTreeViewByTree(json);// url分成一层一层的展示
		}*/
		createTreeViewByTag(json);// tag方式，整个url显示为一层
	}, function(msg){
		Toast.error("获取文档失败，请检查：" + fullUrl);
	}, function(msg){
		projectLoadingIndex--;
		if(projectLoadingIndex == 0) {
			documentLoadFinish();
		}
	});
}

/**
 * 获取所有文档列表提供选择
 * @returns
 */
function getDocumentListByService() {
	$("#choiseDocListUl").empty();
	showGlobalLoadingMessage('获取文档列表中，请稍候...', true);
	ajaxTemp("swagger-mg-ui/document/resourcesList", "post", "json", {}, function(json){
		if(validateResult(json) && json.data.length > 1) {
			showGlobalLoadingMessage('等待选择需展示的文档，请选择...', true);
			for (var i = 0; i < json.data.length; i++) {
				var item = json.data[i];
				$("#choiseDocListUl").append('<li value="'+item+'">'+item+'</li>');
			}
			$('#choiseDocModal').modal({moveable:true, backdrop:'static', keyboard: false});
		} else {
			addDocumentByService();
		}
	});
}

/**
 * 增加文档-通过服务器接口
 * @returns
 */
function addDocumentByService(choiseDocList) {
	// 获取原始的swagger的json对象
	showGlobalLoadingMessage('通过服务器端获取文档中，请稍候...', true);
	ajaxTemp("swagger-mg-ui/document/docs", "post", "json", {choiseDocList: choiseDocList}, function(json){
		//console.log(json);
		showGlobalLoadingMessage('文档获取成功，解析中，请稍候...', true);
		if(isEmptyObject(json) || json.errCode != 200) {
			Toast.error("获取文档错误，请检查！");
			return;
		}
		for (var i = 0; i < json.data.length; i++) {
			showGlobalLoadingMessage('解析第'+(i+1)+'份文档，请稍候...', true);
			var tempDoc = deserialize(json.data[i]);
			console.log(tempDoc);
			documentJsonArr.push(tempDoc);// 加到所有文档
			addHomePageDashboard(tempDoc, tempDoc.fullUrl);
			createDefinitionsMapByJson(tempDoc);
			if(userSettings.catalogShowType == 1) {
				createTreeViewByTree(tempDoc);// url分成一层一层的展示
			} else if(userSettings.catalogShowType == 2){
				createTreeViewByTag(tempDoc);// tag方式，整个url显示为一层
			} else {
				createTreeViewByTree(tempDoc);// url分成一层一层的展示
			}
		}
		documentLoadFinish();
	}, function(msg){
		Toast.error("获取文档失败，请检查！");
	});
}

/**
 * 增加文档
 * @returns
 */
function loadSwaggerResources(fullUrl, isInSetting) {
	// 获取原始的swagger的resources配置
	ajaxTemp(fullUrl, "get", "json", {}, function(json){
		//console.log(json);
		if(isEmptyObject(json) || json.length <= 0) {
			Toast.error("获取文档错误，请检查：" + fullUrl);
			return;
		}
		if(!isInSetting) {
			if(!haveString(userSettings.projects, fullUrl)) {
				userSettings.projects.push(fullUrl);
			}
			storeUserSettings();
			document.location.reload();
		}
		var removedProjects = userSettings.removedProjects;
		if(removedProjects == null || removedProjects == undefined) {
			removedProjects = [];
		}
		// 将：http://192.168.5.172:8081/swagger-resources
		// 变成：http://192.168.5.172:8081/v2/api-docs
		var locationHref = fullUrl.substring(0, fullUrl.lastIndexOf("/"));
		for (var i = 0; i < json.length; i++) {
			var tempLocation = locationHref + json[i].location;
			// 如果是新加的，判断在删除的数组里面，则从删除数组中移除，新的开始
			if(!isInSetting) {
				for (var j = 0; j < removedProjects.length; j++) {
					if(removedProjects[j] == tempLocation) {
						removedProjects[j] = "";
					}
				}
			}
			// 不存在则添加
			if(!haveString(swaggerApiDocsArr, tempLocation)) {
				swaggerApiDocsArr.push(tempLocation);
			}
		}
		// 去掉被删除的路劲
		var removedProjectsNew = [];
		for (var i = 0; i < removedProjects.length; i++) {
			if(isNotEmpty(removedProjects[i])) {
				removedProjectsNew.push(removedProjects[i]);
			}
		}
		userSettings.removedProjects = removedProjectsNew;
		storeUserSettings();
	}, function(msg){
		Toast.error("获取文档失败，请检查：" + fullUrl);
	}, function(msg){
		projectLoadingIndex--;
		showGlobalLoadingMessage('剩余'+projectLoadingIndex+'份文档，请稍候...', true);
		if(projectLoadingIndex == 0) {
			loadSwaggerResourcesFinish();
		}
	});
}

/**
 * 自由拖动改变左右框架的宽度
 */
$("#resizebleLeftRight").mgResizebleWidth({
	prev:"#leftContent",
	prevWtMin: 120, prevWtMax: 999999,
	nextWtMin: 360, nextWtMax: 999999,
	onresize:function(prevWNow, nextWNow){
		changeContentWidth(prevWNow);
	},
	onstart:function(){
		$("body").addClass("unselect");
	},
	onfinish:function(){
		$("body").removeClass("unselect");
		storeUserSettings();
	}
});

/**
 * 切换导航栏的宽度到最小或最大
 */
$("#changeContentWidth").click(function(){
	var isMinWidth = ($("#leftContent").width() == 120);
	changeContentWidth(isMinWidth ? 360 : 120);
});

/**
 * 修改tree的class
 */
$("input[name='treeShowType']").change(function() {
	userSettings.treeShowType = $("input[name='treeShowType']:checked").val();
	updateTreeShowType();
	storeUserSettings();
});

/**
 * 切换url分成一层一层的展示、整个url显示为一层展示
 */
$("input[name='catalogShowType']").change(function() {
	userSettings.catalogShowType = $("input[name='catalogShowType']:checked").val();
	regeneratePathTree();
	storeUserSettings();
});

/**
 * 是否展示参数类型
 */
$("input[name='showParamType']").change(function() {
	userSettings.showParamType = $("input[name='showParamType']:checked").val();
	storeUserSettings();
});

/**
 * 是否仅使用上次请求参数
 */
$("input[name='onlyUseLastParam']").change(function() {
	userSettings.onlyUseLastParam = $("input[name='onlyUseLastParam']:checked").val();
	storeUserSettings();
});

/**
 * 是否自动填充请求参数
 */
$("input[name='autoFillParam']").change(function() {
	userSettings.autoFillParam = $("input[name='autoFillParam']:checked").val();
	storeUserSettings();
});

/**
 * 搜索框回车事件
 */
$("#searchDocInput").keyup(function(e) {  
	if (e.keyCode == 13) {
		searchDoc();
	}
});
/**
 * 搜索按钮点击
 */
$("#searchDocBt").click(function(){
	searchDoc();
});

/**
 * 增加文档
 */
$("#addNewDocument").click(function(){
	$('#addNewDocumentInput').val("");
	$('#addNewDocumentModal').modal({moveable:true});
});

/**
 * 导出文档
 */
$("#exportDocument").click(function(){
	// 清空
	$("#exportDocumentText").val("");
	$("#exportDocumentUl").show();
	$("#exportDocumentModal .bottom-box").show();
	$("#exportDocumentModal .alert").addClass("hidden");
	$("#exportDocumentText").addClass("hidden");
	
	$('#exportDocumentUl').empty();
	for (var i = 0; i < documentJsonArr.length; i++) {
		$("#exportDocumentUl").append('<li index='+i+'>'+decodeURI(documentJsonArr[i].fullUrl)+'</li>');
	}
	$('#exportDocumentModal').modal({moveable:true, backdrop: 'static'});
	$('#exportDocumentModal').modal('ajustPosition', 'fit');
});

$("#doExportBtn").click(function(){
	var jsonStrAll = "";
	var mgUiDataArr = "var mgUiDataArr = [";
	$('#exportDocumentUl li.checked').each(function(i){
		var index = $(this).attr("index");
		var jsonObj = documentJsonArr[index];
		var jsonStr = JSON.stringify(jsonObj);
		jsonStrAll += "var mgUiData" + index + "=" + jsonStr + ";\n\n";
		if(i > 0) {
			mgUiDataArr += ", ";
		}
		mgUiDataArr += "mgUiData" + index;
	});
	if(isEmpty(jsonStrAll)){
		Toast.warn("请输选择需要导出的文档");return;
	}
	jsonStrAll += mgUiDataArr + "];";
	$("#exportDocumentText").val(jsonStrAll);
	$("#exportDocumentUl").hide();
	$("#exportDocumentModal .bottom-box").hide();
	$("#exportDocumentModal .alert").removeClass("hidden");
	$("#exportDocumentText").removeClass("hidden");
	$('#exportDocumentModal').modal('ajustPosition', 'fit');
});

$(".choise").on("click", "li", function(){
	$(this).toggleClass("checked");
});

/**
 * 恢复默认
 */
$("#restoreDefault").click(function(){
	userSettings = defaultUserSettings;
	$("input[name='treeShowType'][value='"+userSettings.treeShowType+"']").prop("checked",true);
	$("input[name='catalogShowType'][value='"+userSettings.catalogShowType+"']").prop("checked",true);
	$("input[name='showParamType'][value='"+userSettings.showParamType+"']").prop("checked",true);
	storeUserSettings();
	document.location.reload();
});

/**
 * 导出配置
 */
$("#exportSetting").click(function(){
	$("#exportSettingText").val(JSON.stringify(userSettings));
	$('#exportSettingModal').modal({moveable:true});
});

/**
 * 导入配置
 */
$("#importSetting").click(function(){
	$("#importSettingText").val("");
	$('#importSettingModal').modal({moveable:true});
});

/**
 * 导入配置-确认
 */
$("#importSettingBtn").click(function(){
	var exportSettingText = $("#importSettingText").val();
	userSettings = JSON.parse(exportSettingText);
	$('#importSettingModal').modal('hide');
	storeUserSettings();
});

/**
 * 选择文档-确认
 */
$("#choiseDocBtn").click(function(){
	var choiseDocList = "", choiseDocListObj = [];
	var allDocListObj = [];
	$('#choiseDocListUl li').each(function(k){
		var tempUrl = $(this).attr("value");
		if($(this).hasClass('checked')) {
			if(k == 0){
				choiseDocList = tempUrl;
			}else{
				choiseDocList += ',' + tempUrl;
			}
			choiseDocListObj.push(tempUrl);
		}
		allDocListObj.push(tempUrl);
	});
	if(serverStorage) {
		addDocumentByService(choiseDocList);
	} else {
		if(choiseDocListObj.length <= 0) {
			choiseDocListObj = allDocListObj;
		}
		// 请求url，获取文档配置，增加树形菜单等
		projectLoadingIndex = choiseDocListObj.length;
		for (var i = 0; i < choiseDocListObj.length; i++) {
			addDocumentByUrl(choiseDocListObj[i]);
		}
	}
	$('#choiseDocModal').modal('hide');
});

/**
 * 增加文档的保存按钮
 */
$("#addNewDocumentBtn").click(function(){
	var addNewDocumentInput = $("#addNewDocumentInput").val();
	if(isEmpty(addNewDocumentInput)) {
		Toast.error("地址不可以为空");return;
	}
	projectLoadingIndex = 1;
	if(serverStorage) {
		ajaxTemp("swagger-mg-ui/document/addSwaggerResources", "post", "json", {resourcesUrl: addNewDocumentInput}, function(json){
			if(validateResult(json)) {
				document.location.reload();
			}
		});
	} else {
		loadSwaggerResources(addNewDocumentInput, false);
		$('#addNewDocumentModal').modal('hide');
	}
});

/**
 * 主页li点击事件，展示主页
 */
$("#homePageLi").click(function(){
	$(".tab-page").hide();
	$(".tab-home-page").show();
});

/**
 * 多行编辑header
 */
$("#bulkEditHeaderCheck").change(function() {
	//console.log($(this).prop('checked'));
	if($(this).prop('checked')) {
		var requestHeaderForm = $("#requestHeaderForm").serializeArray();
		var formStr = serializeArrayToBulkEdit(requestHeaderForm);
		$("#bulkEditHeader").val(formStr);
		$("#bulkEditHeader").show();
		$("#requestHeaderForm table").hide();
	} else {
		var bulkEdit = $("#bulkEditHeader").val();
		bulkEditToTable("#tabParamHeader", bulkEdit);
		$("#requestHeaderForm table").show();
		$("#bulkEditHeader").hide();
	}
});

/**
 * 多行编辑form
 */
$("#bulkEditFormCheck").change(function() {
	//console.log($(this).prop('checked'));
	if($(this).prop('checked')) {
		var requestParamForm = $("#requestParamForm").serializeArray();
		var formStr = serializeArrayToBulkEdit(requestParamForm);
		$("#bulkEditForm").val(formStr);
		$("#bulkEditForm").show();
		$("#tabParamTypeForm table").hide();
	} else {
		var bulkEdit = $("#bulkEditForm").val();
		bulkEditToTable("#tabParamTypeForm", bulkEdit);
		$("#tabParamTypeForm table").show();
		$("#bulkEditForm").hide();
	}
});

$('[data-tab]').on('shown.zui.tab', function(e) {
	var href = $(e.target).attr("href");
	if("#tabParamTypeForm" == href) {
		$(".form-to-url").show();
	} else if("#tabParamTypeBody" == href) {
		$(".form-to-url").hide();
	}
	//console.log('当前被激活的标签页', e.target);
	//console.log('上一个标签页', e.relatedTarget);
});

/**
 * api文档最后的节点点击，展示文档页面
 */
$("#apiPathTree").on("click", ".show-doc", function(){
	$(".tab-page").hide();
	$(".tab-document").show();
	var path = $(this).attr("path");
	var data = treePathDataMap.get(path);
	var docInfo = getNotEmptyStr(data.description);
	var docUrl = getNotEmptyStr(data.url);
	if(isEmpty(docInfo)) {
		docInfo = getNotEmptyStr(data.summary);
	}
	// 处理在线调试
	$("#postUrlInput").val(data.domain + docUrl);
	// 处理模拟返回
	$("#simulationResultUrl").text(docUrl);
	$("#simulationResultUrlTest").text(data.domain + docUrl + "?mgUiTestFlag=1");
	$("#simulationResultUrlTest").attr("href", data.domain + docUrl + "?mgUiTestFlag=1");
	$("#simulationResultText").val("");
	getStorage('p-simulation-response-' + docUrl, function(data){
		var resultText = getNotEmptyStr(data);
		resultText = (typeof resultText === 'string') ? resultText : JSON.stringify(resultText, null, 4);
		$("#simulationResultText").val(resultText);
	});
	// 处理在线文档
	$("#docUrl").text(docUrl);
	$("#docRequestMethod").text(getNotEmptyStr(data.method));
	$("#docInfo").text(docInfo);
	$("#docConsumes").text(arrToString(data.consumes));
	$("#docProduces").text(arrToString(data.produces));
	$("#debugRequstType .btn .options").text(data.method.toUpperCase());
	$("#tabParamTypeBody textarea").val("");
	// 遍历参数列表
	$("#docRequestParam table tbody").empty();
	$("#docRequestExample table tbody").empty();
	requestParamObj = {};
	Formatjson.annotationObject = {};
	//addRequestParamObj(requestParamObj, "Content-Type", "string", "header", false, "", arrToString(data.consumes));
	if(isNotEmpty(data.parameters)) {
		Object.keys(data.parameters).forEach(function(key){
			var tempParameters = data.parameters[key];
			var htmlStr = "", htmlStrExample = "";
			var responsesJson = [];
			var responsesExample = [];
			var tempRequestParamObj = [];
			var required = tempParameters.required;
			var paramName = getNotEmptyStr(tempParameters.name);
			var paramType = getNotEmptyStr(tempParameters.type);
			var paramDesc = getNotEmptyStr(tempParameters.description);
			var paramIn = getNotEmptyStr(tempParameters.in);
			var example = getNotEmptyStr(tempParameters.example, tempParameters.default);
			if(isNotEmpty(tempParameters.items)) {
				htmlStr = paramName + "[0]";
				htmlStrExample = paramName + "[0]";
			} else if(isNotEmpty(tempParameters.schema)) {
				if("array" == tempParameters.schema.type) {
					var responsesObj = definitionsDataMap.get(tempParameters.schema.items.$ref);
					if(responsesObj != null) {
						responsesJson[0] = getResponsesJson(responsesObj, "", false, 1);
						responsesExample[0] = getResponsesJson(responsesObj, "", true, 1);
						tempRequestParamObj[0] = getRequestParamObj(responsesObj, "");
					} else {
						responsesJson = [""];
						responsesExample = [""];
						tempRequestParamObj = [""];
						if(tempParameters.schema.items.type == "boolean") {
							responsesJson = [true];
							responsesExample = [true];
							tempRequestParamObj = [true];
						} else if(tempParameters.schema.items.type == "integer") {
							responsesJson = [0];
							responsesExample = [0];
							tempRequestParamObj = [0];
						}
					}
					requestParamObj["p-body-obj"] = tempRequestParamObj;
					var arrTmp = tempParameters.schema.$ref.split("/");
					paramType = arrTmp[arrTmp.length - 1];
					htmlStr = Formatjson.processObjectToHtmlPre(responsesJson, 0, false, false, false, true);
					htmlStrExample = Formatjson.processObjectToHtmlPre(responsesExample, 0, false, false, false, false);
				} else if(isNotEmpty(tempParameters.schema.$ref)){
					var responsesObj = definitionsDataMap.get(tempParameters.schema.$ref);
					var arrTmp = tempParameters.schema.$ref.split("/");
					paramType = arrTmp[arrTmp.length - 1];
					if(isNotEmpty(responsesObj)) {
						responsesJson = getResponsesJson(responsesObj, "", false, 1);
						responsesExample = getResponsesJson(responsesObj, "", true, 1);
						htmlStr = Formatjson.processObjectToHtmlPre(responsesJson, 0, false, false, false, true);
						htmlStrExample = Formatjson.processObjectToHtmlPre(responsesExample, 0, false, false, false, false);
						tempRequestParamObj = getRequestParamObj(responsesObj, "");
						requestParamObj["p-body-obj"] = tempRequestParamObj;
					} else {
						htmlStr = paramName;
						htmlStrExample = paramName;
						addRequestParamObj(requestParamObj, paramName, paramType, paramIn, required, paramDesc, example);
					}
				} else if("string" == tempParameters.schema.type) {
					htmlStr = paramName;
					htmlStrExample = paramName;
					addRequestParamObj(requestParamObj, paramName, paramType, paramIn, required, paramDesc, example);
				} else {
					htmlStr = paramName;
					htmlStrExample = paramName;
					addRequestParamObj(requestParamObj, paramName, paramType, paramIn, required, paramDesc, example);
				}
			} else {
				htmlStr = paramName;
				htmlStrExample = paramName;
				addRequestParamObj(requestParamObj, paramName, paramType, paramIn, required, paramDesc, example);
			}
			$("#docRequestParam table tbody").append(
				'<tr>'
					+'<td>' + htmlStr + '</td>'
					+'<td>' + paramDesc + '</td>'
					+'<td>' + paramType + '</td>'
					+'<td>' + paramIn + '</td>'
					+'<td>' + required + '</td>'
				+'</tr>'
			);
			$("#docRequestExample table tbody").append(
				'<tr>'
					+'<td>' + htmlStrExample + '</td>'
					+'<td>' + paramDesc + '</td>'
					+'<td>' + paramType + '</td>'
					+'<td>' + paramIn + '</td>'
					+'<td>' + required + '</td>'
				+'</tr>'
			);
		});
	}
//	console.log(requestParamObj);
//	var htmlStrTemp = Formatjson.processObjectToHtmlPre(requestParamObj, 0, false, false, false);
//	$("#htmlStrTemp").html(htmlStrTemp);
	$("#htmlStrTemp").parents("tr").hide();
	createOnlineDebugRequestParam(requestParamObj, getNotEmptyStr(data.url));
	// 遍历结果集列表
	$("#docResponseModel table tbody").empty();
	$("#docResponseExample table tbody").empty();
	Formatjson.annotationObject = {};
	if(isNotEmpty(data.responses)) {
		Object.keys(data.responses).forEach(function(key){
			var tempRespones = data.responses[key];
			if(isNotEmpty(tempRespones.schema)) {
				var responsesJson, responsesExample;
				if("array" == tempRespones.schema.type) {
					responsesJson = [];
					responsesExample = [];
					var responsesObj = definitionsDataMap.get(tempRespones.schema.items.$ref);
					if(responsesObj != null) {
						responsesJson[0] = getResponsesJson(responsesObj, "", false, 1);
						responsesExample[0] = getResponsesJson(responsesObj, "", true, 1);
					} else {
						responsesJson = [""];
						responsesExample = [""];
						if(tempParameters.schema.items.type == "boolean") {
							responsesJson = [true];
							responsesExample = [true];
						} else if(tempParameters.schema.items.type == "integer") {
							responsesJson = [0];
							responsesExample = [0];
						}
					}
				} else if(isNotEmpty(tempRespones.schema.$ref)){
					var responsesObj = definitionsDataMap.get(tempRespones.schema.$ref);
					if(isEmptyObject(responsesObj)) {
						var arrTmp = tempRespones.schema.$ref.split("/");
						var lastObjName = arrTmp[arrTmp.length - 1];
						responsesJson = lastObjName;
						responsesExample = lastObjName;
					} else {
						responsesJson = getResponsesJson(responsesObj, "", false, 1);
						responsesExample = getResponsesJson(responsesObj, "", true, 1);
					}
				} else {
					responsesJson = "";
					responsesExample = "";
				}
				if(isEmptyObject(responsesJson)) {
					return;
				}
				//console.log(Formatjson.annotationObject);
				var htmlStr = Formatjson.processObjectToHtmlPre(responsesJson, 0, false, false, false, true);
				$("#docResponseModel table tbody").append(
					'<tr>'
						+'<td>' + key + '</td>'
						+'<td>' + htmlStr + '</td>'
					+'</tr>'
				);
				htmlStr = Formatjson.processObjectToHtmlPre(responsesExample, 0, false, false, false, false);
				$("#docResponseExample table tbody").append(
					'<tr>'
						+'<td>' + key + '</td>'
						+'<td>' + htmlStr + '</td>'
					+'</tr>'
				);
			}
		});
	}
});

/**
 * 新增全局请求参数对象
 */
function addRequestParamObj(paramObj, paramName, paramType, paramIn, required, paramDesc, value) {
	var tempObj = {};
	tempObj.paramType = paramType;
	tempObj.paramIn = paramIn;
	tempObj.required = required;
	tempObj.paramDesc = paramDesc;
	tempObj.isParamObj = true;
	tempObj.value = value;
	paramObj[paramName] = tempObj;
}

/**
 * 搜索文档
 * @returns
 */
function searchDoc() {
	var keywords = $("#searchDocInput").val();
	// 重新生成
	regeneratePathTree(keywords);
	if (isEmpty(keywords)){
		return;
	}
	$('#apiPathTree .projects').tree('expand');
//	// 先全部收起
//	$('#apiPathTree .projects').tree('collapse');
//	for (var mapData of treePathDataMap) {
//		if (mapData[0].toLowerCase().indexOf(keywords.toLowerCase()) >= 0) {
//			var treeNode = $('#apiPathTree .projects').find("[m-id="+mapData[1].treeId+"]");
//			$('#apiPathTree .projects').tree('show', treeNode);
//		}
//	}
}

// 重新生成文档
function regeneratePathTree(keywords){
	projectTreeIdIndex = 1;
	treePathDataMap = new Map();
	$('#apiPathTree').empty();
	$('#apiPathTree').append('<ul class="tree tree-lines projects"></ul>');
	for (var i = 0; i < documentJsonArr.length; i++) {
		var json = documentJsonArr[i];
		if(userSettings.catalogShowType == 1) {
			createTreeViewByTree(json, keywords);// url分成一层一层的展示
		} else if(userSettings.catalogShowType == 2){
			createTreeViewByTag(json, keywords);// tag方式，整个url显示为一层
		} else {
			createTreeViewByTree(json, keywords);// url分成一层一层的展示
		}
	}
	$('#apiPathTree .projects').tree();
	updateTreeShowType();
}

function findInPathsValue(key, pathsValue, keywords) {
	if(isEmpty(keywords) || isEmpty(key)) {
		return true;
	}
	key = key.toLowerCase();
	keywords = keywords.toLowerCase();
	// 路径中有就不用再去找了
	if(key.indexOf(keywords) >= 0) {
		return true;
	}
	for ( var subKey in pathsValue) {
		// 找路径和说明里面包含关键字的
		var tagsTmp = pathsValue[subKey].tags;
		var pathTmp = pathsValue[subKey].path;
		var summaryTmp = pathsValue[subKey].summary;
		var descriptionTmp = pathsValue[subKey].description;
		if(isNotEmpty(pathTmp) && pathTmp.toLowerCase().indexOf(keywords) >= 0) {
			return true;
		}
		if(isNotEmpty(summaryTmp) && summaryTmp.toLowerCase().indexOf(keywords) >= 0) {
			return true;
		}
		if(isNotEmpty(descriptionTmp) && descriptionTmp.toLowerCase().indexOf(keywords) >= 0) {
			return true;
		}
		if(isNotEmpty(tagsTmp) && arrToString(tagsTmp).toLowerCase().indexOf(keywords) >= 0) {
			return true;
		}
	}
	return false;
}

function getResponsesJson(responsesObj, prevRef, isExample, recursiveCount) {
	var responsesJson = {};
	recursiveCount++;// 多层递归，最多递归10层，防止无限递归
	if(isEmpty(responsesObj) || isEmpty(responsesObj.properties) || recursiveCount > 10) {
		return responsesJson;
	}
	var requiredArr = responsesObj.required;
	Object.keys(responsesObj.properties).forEach(function(prop){
		var tmpData = responsesObj.properties[prop];
		if("array" == tmpData.type) {// 数组
			Formatjson.annotationObject[prop] = getNotEmptyStr(tmpData.description);
			if(prevRef != tmpData.items.$ref) {
				var tempObj = definitionsDataMap.get(tmpData.items.$ref);
				if(tempObj != null) {
					var tempArr = responsesJson[prop] = [];
					tempArr[0] = getResponsesJson(tempObj, tmpData.items.$ref, isExample, recursiveCount);
				} else {
					var responsesJsonSub = [];
					var bodyFor = responsesJsonSub;
					var items = tmpData.items;
					for (var i = 0; i < 10; i++) {
						if("array" == items.type) {
							bodyFor = bodyFor[0] = [];
							items = items.items;
						} else {
							tempObj = definitionsDataMap.get(items.$ref);
							if(tempObj != null) {
								bodyFor[0] = getResponsesJson(tempObj, items.$ref, isExample, recursiveCount);
							} else {
								if(items.type == "boolean") {
									bodyFor[0] = true;
								} else if(items.type == "integer") {
									bodyFor[0] = 0;
								} else {
									bodyFor[0] = "";
								}
							}
							break;
						}
					}
					responsesJson[prop] = responsesJsonSub;
				}
			} else {
				responsesJson[prop] = "{}" + getNotEmptyStr(tmpData.description);
			}
		} else if(isNotEmpty(tmpData.$ref)) {// 对象
			Formatjson.annotationObject[prop] = getNotEmptyStr(tmpData.description);
			if(prevRef != tmpData.$ref) {
				var tempObj = definitionsDataMap.get(tmpData.$ref);
				responsesJson[prop] = getResponsesJson(tempObj, tmpData.$ref, isExample, recursiveCount);
			} else {
				responsesJson[prop] = "{}" + getNotEmptyStr(tmpData.description);
			}
		} else {// 字段
			var enumExample = "";
			var enumObj = tmpData["enum"];
			if(!isEmptyObject(enumObj) && enumObj.length > 0) {
				enumExample = "枚举值：";
				for (var i = 0; i < enumObj.length; i++) {
					if(i > 0) {enumExample += "、";}
					enumExample += enumObj[i];
				}
			}
			var typeStr  = getNotEmptyStr(tmpData.format);
			if(isEmpty(typeStr)) {
				typeStr = getNotEmptyStr(tmpData.type);
			}
			if(isExample) {
				var tempVal = getNotEmptyStr(tmpData.example);
				if(isEmpty(tempVal)) {
					tempVal = getAutoFillValue(typeStr, prop);
				}
				if(isNotEmpty(tempVal) && isNotEmpty(enumExample)) {
					tempVal = tempVal + "，" + enumExample;
				}
				responsesJson[prop] = tempVal;
			} else {
				if(userSettings.showParamType == 1) {
					if(haveString(requiredArr, prop)) {
						typeStr = (isNotEmpty(typeStr) ? typeStr + "," : "") + "required";
					}
					if(isNotEmpty(typeStr)) {
						typeStr = "(" + typeStr + ")";
					}
				}
				var descriptionStr = typeStr + getNotEmptyStr(tmpData.description);
				if(isNotEmpty(descriptionStr) && isNotEmpty(enumExample)) {
					descriptionStr = descriptionStr + "，" + enumExample;
				}
				responsesJson[prop] = descriptionStr;
			}
		}
	});
	return responsesJson;
}

function getRequestParamObj(responsesObj, prevRef) {
	var responsesJson = {};
	if(isEmpty(responsesObj) || isEmpty(responsesObj.properties)) {
		return responsesJson;
	}
	var requiredArr = responsesObj.required;
	Object.keys(responsesObj.properties).forEach(function(prop){
		var tmpData = responsesObj.properties[prop];
		if("array" == tmpData.type) {// 数组
			if(prevRef != tmpData.items.$ref) {
				var tempObj = definitionsDataMap.get(tmpData.items.$ref);
				if(tempObj != null) {
					var tempArr = responsesJson[prop] = [];
					tempArr[0] = getRequestParamObj(tempObj, tmpData.items.$ref);
				} else {
					var responsesJsonSub = [];
					var bodyFor = responsesJsonSub;
					var items = tmpData.items;
					for (var i = 0; i < 10; i++) {
						if("array" == items.type) {
							bodyFor = bodyFor[0] = [];
							items = items.items;
						} else {
							tempObj = definitionsDataMap.get(items.$ref);
							if(tempObj != null) {
								bodyFor[0] = getRequestParamObj(tempObj, items.$ref);
							} else {
								if(items.type == "boolean") {
									bodyFor[0] = true;
								} else if(items.type == "integer") {
									bodyFor[0] = 0;
								} else {
									bodyFor[0] = "";
								}
							}
							break;
						}
					}
					responsesJson[prop] = responsesJsonSub;
				}
			} else {
				var required = haveString(requiredArr, prop);
				var paramType = getNotEmptyStr(tmpData.format);
				var paramDesc = getNotEmptyStr(tmpData.description);
				var example = getNotEmptyStr(tmpData.example, tmpData.default);
				if(isEmpty(paramType)) {
					paramType = getNotEmptyStr(tmpData.type);
				}
				addRequestParamObj(responsesJson, prop, paramType, "", required, paramDesc, example);
			}
		} else if(isNotEmpty(tmpData.$ref)) {// 对象
			if(prevRef != tmpData.$ref) {
				var tempObj = definitionsDataMap.get(tmpData.$ref);
				responsesJson[prop] = getRequestParamObj(tempObj, tmpData.$ref);
			} else {
				var required = haveString(requiredArr, prop);
				var paramType = getNotEmptyStr(tmpData.format);
				var paramDesc = getNotEmptyStr(tmpData.description);
				var example = getNotEmptyStr(tmpData.example, tmpData.default);
				if(isEmpty(paramType)) {
					paramType = getNotEmptyStr(tmpData.type);
				}
				addRequestParamObj(responsesJson, prop, paramType, "", required, paramDesc, example);
			}
		} else {// 字段
			var required = haveString(requiredArr, prop);
			var paramType = getNotEmptyStr(tmpData.format);
			var paramDesc = getNotEmptyStr(tmpData.description);
			var example = getNotEmptyStr(tmpData.example, tmpData.default);
			if(isEmpty(paramType)) {
				paramType = getNotEmptyStr(tmpData.type);
			}
			addRequestParamObj(responsesJson, prop, paramType, "", required, paramDesc, example);
		}
	});
	return responsesJson;
}

/**
 * 修改左右框架的宽度
 * @param width 左侧导航栏的宽度
 * @returns
 */
function changeContentWidth(width) {
	$("#leftContent").css("width", width + 'px');
	$("#resizebleLeftRight").css("left", width + 'px');
	$("#rightContent").css("left", width + 'px');
	var logoText = "接口管理";
	if(width < 270 && width > 140){
		logoText = "mg-ui";
	} else if(width < 140){
		logoText = "mui";
	}
	$("#logoText").text(logoText);
	userSettings.prevWNow = width;
}

/**
 * 通过原始json生成引用的字典Map
 * @param json swagger的原始json
 * @returns
 */
function createDefinitionsMapByJson(json) {
	var pathIndex = {};
	var definitions = json.definitions;
	//console.log(paths);
	if(isNotEmpty(definitions)) {
		Object.keys(definitions).forEach(function(key){
			//console.log(key);
			definitionsDataMap.set("#/definitions/" + key, definitions[key]);
		});
	}
}

/**
 * 增加项目文档
 * @param json
 * @returns
 */
function addHomePageDashboard(json, fullUrl) {
	var info = json.info||{};
	var contactName = "";
	if(isNotEmpty(info.contact)) {
		contactName = "昵称：" + getNotEmptyStr(info.contact.name, "-");
		contactName += "<br/>邮箱：" + getNotEmptyStr(info.contact.email, "-");
		contactName += "<br/>网站：" + getNotEmptyStr(info.contact.url, "-");
	}
	$("#homePageDashboard section").append(
		'<div class="col-md-6 col-sm-6">'
			+'<div class="panel" data-id="'+fullUrl+'">'
				+'<div class="panel-heading">'
					+'<div class="title">'+info.title+'</div>'
					+'<div class="panel-actions">'
						+'<button type="button" class="btn remove-panel" data-placement="bottom">'
							+'<i class="icon-remove"></i>'
						+'</button>'
					+'</div>'
				+'</div>'
				+'<div class="panel-body">'
					+'<div class="content">'
						+'<table class="table table-bordered setting-table">'
							+'<tr>'
								+'<td class="info">简介</td>'
								+'<td>'+info.description+'</td>'
							+'</tr>'
							+'<tr>'
								+'<td class="info">版本</td>'
								+'<td>'+info.version+'</td>'
							+'</tr>'
							+'<tr>'
								+'<td class="info">地址</td>'
								+'<td>'+decodeURI(fullUrl)+'</td>'
							+'</tr>'
						+'</table>'
					+'</div>'
				+'</div>'
			+'</div>'
		+'</div>'
	);
}

/**
 * 初始化用户的设置
 * @param 
 * @returns
 */
function documentLoadFinish() {
	showGlobalLoadingMessage('文档解析完成！', false);
	setTimeout(function() {
		globalLoadingMessager.hide();
	}, 1000);
	$('#apiPathTree .projects').tree();
	$('#homePageDashboard').dashboard({draggable: true,
		// 修改排序
		afterOrdered:function(newOrders){
			/*//console.log(newOrders);
			var tempProjects = [], nowIndex = 0, settingTableIndex = 1;
			Object.keys(newOrders).forEach(function(key){
				if(key == 1) {
					settingTableIndex = newOrders[key];
					return;
				}
				var subCount = (newOrders[key] > settingTableIndex) ? 2 : 1;
				tempProjects[newOrders[key] - subCount] = key;
			});
			userSettings.projects = tempProjects;
			storeUserSettings();
			*/
			// 应该是控件问题，拖动之后单选项被值空了，需要重新设置选中，而且还必须等一会设置才有效果。。
			setTimeout("updateUserSettingsUi();", 500);
		},
		// 修改大小
		onResize:function(){
			
		},
		// 被移除
		afterPanelRemoved:function(id){
			//console.log(id);
			if(serverStorage) {
				ajaxTemp("swagger-mg-ui/document/deleteSwaggerDoc", "post", "json", {docUrl: id}, function(json){
					if(validateResult(json)) {
						document.location.reload();
					}
				});
			} else {
				var tempProjects = [], nowIndex = 0;
				for ( var i = 0; i < userSettings.projects.length; i++){
					if(userSettings.projects[i] != id) {
						tempProjects[nowIndex++] = userSettings.projects[i];
					}
				}
				userSettings.projects = tempProjects;
				userSettings.removedProjects.push(id);
				storeUserSettings();
			}
		},
	});
}

/**
 * 修改用户的选项的显示
 * @param 
 * @returns
 */
function updateUserSettingsUi() {
	$("input[name='treeShowType'][value='"+userSettings.treeShowType+"']").prop("checked",true);
	$("input[name='catalogShowType'][value='"+userSettings.catalogShowType+"']").prop("checked",true);
	$("input[name='showParamType'][value='"+userSettings.showParamType+"']").prop("checked",true);
	$("input[name='onlyUseLastParam'][value='"+userSettings.onlyUseLastParam+"']").prop("checked",true);
	$("input[name='autoFillParam'][value='"+userSettings.autoFillParam+"']").prop("checked",true);
	if(!serverStorage) {
		$(".local-storage").show();// 服务器存储了就不用管理了
	}
}

/**
 * 修改树形菜单展示类型
 * @param 
 * @returns
 */
function updateTreeShowType() {
	$('#apiPathTree .projects').removeClass("tree-angles tree-menu tree-folders tree-chevrons");
	//tree-angles、2=tree-menu、3=默认，4=tree-folders、5=tree-chevrons
	$('#apiPathTree .projects').addClass("tree-lines");
	var treeShowType = "tree-angles";
	if(userSettings.treeShowType == 1) {
		treeShowType = "tree-angles";
	} else if(userSettings.treeShowType == 2) {
		treeShowType = "tree-menu";
		$('#apiPathTree .projects').removeClass("tree-lines");
	} else if(userSettings.treeShowType == 3) {
		treeShowType = "";
	} else if(userSettings.treeShowType == 4) {
		treeShowType = "tree-folders";
	} else if(userSettings.treeShowType == 5) {
		treeShowType = "tree-chevrons";
	} else {
		userSettings.treeShowType = 1;
	}
	if(isNotEmpty(treeShowType)) {
		$('#apiPathTree .projects').addClass(treeShowType);
	}
}

/**
 * 存储用户设置
 */
function storeUserSettings() {
	setStorage('userSettings', userSettings);
}

/**
 * 获取数据，异步的操作
 */
function getStorage(key, success, fail) {
	if(serverStorage) {
		ajaxTemp("swagger-mg-ui/storage/data", "get", "json", {key: key}, function(json){
			if(json.errCode == 200) {
				if(typeof success == "function") {
					var result = deserialize(json.data);
					success(result);
				}
			} else {
				if(typeof fail == "function") {
					fail();
				}
			}
		}, function(msg){
			if(typeof fail == "function") {
				fail();
			}
		});
	} else {
		var value = $.zui.store.get(key);
		if(typeof success == "function") {
			success(value);
		}
	}
}

/**
 * 存储数据，异步的操作
 */
function setStorage(key, value, success, fail) {
	if(serverStorage) {
		value = $.zui.store.serialize(value);
		ajaxTemp("swagger-mg-ui/storage/data", "post", "json", {key: key, value: value}, function(json){
			if(json.errCode == 200) {
				if(typeof success == "function") {
					success();
				}
			} else {
				if(typeof fail == "function") {
					fail(getNotEmptyStr(json.errMsg));
				}
			}
		}, function(msg){
			if(typeof fail == "function") {
				fail("");
			}
			console.log("存储数据到服务器失败，请检查");
		});
	} else {
		$.zui.store.set(key, value);
	}
}

/**
 * 初始化用户的设置--通过服务器数据
 * @param 
 * @returns
 */
function initUserSettingsByServer() {
	
}

/**
 * 初始化用户的设置
 * @param 
 * @returns
 */
function initUserSettings() {
	getStorage('userSettings', function(data) {
		userSettings = data;
		if(isEmpty(userSettings) || isEmptyObject(userSettings)) {
			userSettings = defaultUserSettings;
		}
		changeContentWidth(userSettings.prevWNow);
		updateTreeShowType();
		updateUserSettingsUi();
		// 增加文档
		if(serverStorage) {
			getDocumentListByService();
		} else {
			var projects = userSettings.projects;
			if(isEmptyObject(projects) || projects.length <= 0) {
				// 没有文档时默认本项目的文档
				userSettings.projects = [];
				var locationHref = document.location.href;
				locationHref = locationHref.substring(0, locationHref.lastIndexOf("/"));
				projectLoadingIndex = 1;
				loadSwaggerResources(locationHref + "/swagger-resources", false);
			} else {
				projectLoadingIndex = projects.length;
				for ( var i = 0; i < projects.length; i++){
					// 请求url，获取文档配置，增加树形菜单等
					loadSwaggerResources(projects[i], true);
				}
			}
		}
	});
}

function showGlobalLoadingMessage(text, loading) {
	if(loading) {
		text += '<i class="icon icon-spin icon-spinner-snake hide"></i>';
	}
	globalLoadingMessager.$.find(".messager-content").html(text);
}

function loadSwaggerResourcesFinish() {
	projectLoadingIndex = swaggerApiDocsArr.length;
	if (projectLoadingIndex <= 0) {
		documentLoadFinish();
	} else {
		if(swaggerApiDocsArr.length > 1) {
			showGlobalLoadingMessage('等待选择需展示的文档，请选择...', true);
			for (var i = 0; i < swaggerApiDocsArr.length; i++) {
				var item = swaggerApiDocsArr[i];
				$("#choiseDocListUl").append('<li value="'+item+'">'+item+'</li>');
			}
			$('#choiseDocModal').modal({moveable:true, backdrop:'static', keyboard: false});
		} else {
			for ( var i = 0; i < swaggerApiDocsArr.length; i++){
				// 请求url，获取文档配置，增加树形菜单等
				addDocumentByUrl(swaggerApiDocsArr[i]);
			}
		}
		swaggerApiDocsArr = [];
	}
}
