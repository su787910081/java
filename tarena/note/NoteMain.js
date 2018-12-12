var strDivPrevIdName = "divIdStair";    // 每一个需要展开或者隐藏的DIV ID 名前缀
var strFuncName = "toggleDiv";          // 每一个需要展开或者隐藏的DIV 添加点击事件
var strPStair = "pStair";               // 每一个需要展开或者隐藏的P 标签 的class 值
var strDivStair = "divStair";           // 每一个需要展开或者隐藏的的DIV 添加统一的class 值 

// 隐藏所有CLASS 的值 为 "divStair" 的div
function hiddenAllDivStair(curParentNode) {
    var divBossChilds = curParentNode.getElementsByTagName("div");
    for (var i = 0; i <divBossChilds.length; i++) {
        var theDiv = divBossChilds[i];
        if (theDiv.className == "divStair") {
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


// 对所有该有的超链接 进行初始化
function initLink(arrChilds, strCurDivPrevIdName) {
    if (!arrChilds) {
        return true;
    }

    for (var i = 0; i < arrChilds.length; i++) {
        var pNode = arrChilds[i];
        if (pNode.className != strPStair) {
            continue;
        }

        var divNode = arrChilds[++i];
        if (divNode.tagName != "DIV") {
            return false;
        }

        var strDivIdName_i = strCurDivPrevIdName + "_" + i;
        var strHTML = pNode.innerHTML;
        var strNewHTML = '<a href="JavaScript:void(0)" onclick="'
            + strFuncName + '(' + "'" + strDivIdName_i + "'"
            + ')">' + strHTML + '</a>';

        pNode.innerHTML = strNewHTML;

        divNode.setAttribute("id", strDivIdName_i);
        divNode.setAttribute("class", strDivStair);

        // 递归为每一个DIV 做判断，它下面的所有节点是否需要添加展开或者隐藏功能
        initLink($(divNode).children(), strDivIdName_i);
    }
}