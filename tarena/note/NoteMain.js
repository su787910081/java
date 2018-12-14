/* 
 * 所有需要处理为超链接的元素标签(Element)必须紧跟着一个DIV 标签元素
 * 同时此Element 或者它的子元素至少有一个添加了class="linkStair"，且这个元素的内容将被直接处理成链接链接
 * 添加一个规则，所有的超链接标签元素都必须放在一个span 标签里面，这样做的目的是防止其它标签里面有多个class 值时，元素内容被添加到a 标签里面而无法加载到css 的样式
 * 即： 超链接必须 是span 标签 ，同时拥有class="linkstart"
 */

var strDivPrevIdName = "divIdStair";    // 每一个需要展开或者隐藏的DIV ID 名前缀
var strFuncName = "toggleDiv";          // 每一个需要展开或者隐藏的DIV 添加点击事件
var strLinkStair = "linkStair";         // 每一个需要展开或者隐藏的元素标签 的class 值
var strDivStair = "divStair";           // 每一个需要展开或者隐藏的的DIV 添加统一的class 值 

// 隐藏所有CLASS 的值 为 "divStair" 的div
function hiddenAllDivStair(curParentNode) {
    var divBossChilds = curParentNode.getElementsByTagName("div");
    for (var i = 0; i <divBossChilds.length; i++) {
        var theDiv = divBossChilds[i];
        if (theDiv.className == strDivStair) {
            theDiv.style.display = "none";
        }
    }
}

// 切换选中div 元素的显示方式
function toggleDiv(divStairId) {
    var divNode = document.getElementById(divStairId);

    var flagDisplay = "block";
    if (divNode.style.display == "block") {
        flagDisplay = "none";
    }

    hiddenAllDivStair(divNode.parentNode);

    if (flagDisplay == "block") {
        divNode.style.display = flagDisplay;

        var divParent = divNode.parentNode;
        while (divParent.id != "divBoss") {
            divParent.style.display = "block";
            divParent = divParent.parentNode;
        }
    }
}

function init(strDivIdRoot) {
    if (!strDivIdRoot) {
        strDivIdRoot = "divBoss";
    }

    var arrChilds = $("#" + strDivIdRoot).children();
    initLink(arrChilds, strDivPrevIdName);
    // 初始化所有CLASS 值 为"divStair" 的div
    var divBoss = document.getElementById(strDivIdRoot);
    hiddenAllDivStair(divBoss);
}

// 对所有该有的超链接 进行初始化
/*
 * 1. Element(^DIV) + Element(DIV);
 * 2. if (Element(^DIV).className == strLinkStair) 则整个Element(%DIV) 添加一个超链接;
 * 3. else  and if (Element(^DIV).children().className == strLinkStair) 则添加children() 超链接;
 */
function initLink(arrChilds, strCurDivPrevIdName) {
    if (!arrChilds) {
        return true;
    }

    // var len = arrChilds.length;
    for (var i = 0; i < arrChilds.length; i++) {
        var strDivDefaultIdName_i = strCurDivPrevIdName + "_" + i;
        var curNode = arrChilds[i];
        if (curNode.tagName == "DIV") {
            // 递归为每一个DIV 做判断，它下面的所有节点是否需要添加展开或者隐藏功能
            initLink($(curNode).children(), strDivDefaultIdName_i);
            continue;
        }

        // assert(curNode.tagName != "DIV");    // 当前节点一定不是DIV 节点
        var linkNodes = findLinkNode(curNode);
        if (!linkNodes || linkNodes.length == 0) {
            continue;
        }

        // 若当前节点存在符合条件的超链接节点，则检查它的蚂蚱的兄弟节点是否为DIV 节点
        // 若是DIV 节点 ，则认定该DIV节点为当前节点的超链接节点
        var divNode = arrChilds[i+1];
        if (divNode.tagName != "DIV") {
            continue;
        }

        var strDivIdName = strDivDefaultIdName_i;
        if (divNode.id) {
            strDivIdName = divNode.id;
        }

        for (var j = 0; j < linkNodes.length; j++) {
            var linkNode = linkNodes[j];
            var strHTML = linkNode.innerHTML;
            var strNewLinkNode = '<a href="JavaScript:void(0)" onclick="'
                + strFuncName + '(' + "'" + strDivIdName + "'"
                + ')">' + strHTML + '</a>';

            linkNode.innerHTML = strNewLinkNode;

            // var linkANode = $(strNewLinkNode);
            // $(linkNode).append(linkANode);
        }

        // divNode.setAttribute("id", strDivIdName);
        divNode.id = strDivIdName;
        divNode.setAttribute("class", strDivStair);
    }
}

// 指定节点元素是否为满足条件的超链接节点为，或者其子节点是否有满足条件的超链接节点
function findLinkNode(curNode) {
    var linkNodes = [];
    if (enableLink(curNode)) {
        linkNodes.push(curNode);
    } else {
        var arrChilds = $(curNode).children();
        if (arrChilds) {
            for (var i = 0; i < arrChilds.length; i++) {
                var ch = arrChilds[i];
                if (enableLink(ch)) {
                    linkNodes.push(ch);
                }
            }
        }
    }

    return linkNodes;
}

function enableLink(curNode) {
    do {
        if (!curNode) {
            break;
        }
        if (!curNode.className) {
            break;
        }
        if (curNode.tagName != "SPAN") {
            break;
        }

        var arrClasses = curNode.className.split(/[,\s]/); // 正则拆分子串
        for (var i = 0; i < arrClasses.length; i++) {
            if (arrClasses[i] == strLinkStair) {
                return true;
            }
        }
    } while (false);

    return false;
}