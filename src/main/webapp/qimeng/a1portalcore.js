var A1PORTAL_COL_DELIMITER=String.fromCharCode(16);var A1PORTAL_ROW_DELIMITER=String.fromCharCode(15);var __a1portal_m_bPageLoaded=false;window.onload=__a1portal_Page_OnLoad;function __a1portal_ClientAPIEnabled(){return typeof(a1portal)!="undefined"}function __a1portal_Page_OnLoad(){if(__a1portal_ClientAPIEnabled()){var sLoadHandlers=a1portal.getVar("__a1portal_pageload");if(sLoadHandlers!=null){eval(sLoadHandlers)}a1portal.dom.attachEvent(window,"onscroll",__a1portal_bodyscroll)}__a1portal_m_bPageLoaded=true}function __a1portal_KeyDown(iKeyCode,sFunc,e){if(e==null){e=window.event}if(e.keyCode==iKeyCode){eval(unescape(sFunc));return false}}function __a1portal_bodyscroll(){var a=document.forms.Form;if(__a1portal_ClientAPIEnabled()&&__a1portal_m_bPageLoaded){a.ScrollTop.value=document.documentElement.scrollTop?document.documentElement.scrollTop:a1portal.dom.getByTagName("body")[0].scrollTop}}function __a1portal_setScrollTop(c){if(__a1portal_ClientAPIEnabled()){if(c==null){c=document.forms.Form.ScrollTop.value}var a=a1portal.getVar("ScrollToControl");if(a!=null&&a.length>0){var b=a1portal.dom.getById(a);if(b!=null){c=a1portal.dom.positioning.elementTop(b);a1portal.setVar("ScrollToControl","")}}window.scrollTo(0,c)}}function __a1portal_SetInitialFocus(a){var b=a1portal.dom.getById(a);if(b!=null&&__a1portal_CanReceiveFocus(b)){b.focus()}}function __a1portal_CanReceiveFocus(b){if(b.style.display!="none"&&b.tabIndex>-1&&b.disabled==false&&b.style.visible!="hidden"){var a=b.parentElement;while(a!=null&&a.tagName!="BODY"){if(a.style.display=="none"||a.disabled||a.style.visible=="hidden"){return false}a=a.parentElement}return true}else{return false}}function __a1portal_ContainerMaxMin_OnClick(h,b){var f=a1portal.dom.getById(b);if(f!=null){var e=h.childNodes[0];var k=h.getAttribute("containerid");var i=h.getAttribute("cookieid");var d=e.src.toLowerCase().substr(e.src.lastIndexOf("/"));var a;var g;var j;if(a1portal.getVar("min_icon_"+k)){j=a1portal.getVar("min_icon_"+k)}else{j=a1portal.getVar("min_icon")}if(a1portal.getVar("max_icon_"+k)){g=a1portal.getVar("max_icon_"+k)}else{g=a1portal.getVar("max_icon")}a=g.toLowerCase().substr(g.lastIndexOf("/"));var c=5;if(h.getAttribute("animf")!=null){c=new Number(h.getAttribute("animf"))}if(d==a){e.src=j;a1portal.dom.expandElement(f,c);e.title=a1portal.getVar("min_text");if(i!=null){if(a1portal.getVar("__a1portal_"+k+":defminimized")=="true"){a1portal.dom.setCookie(i,"true",365)}else{a1portal.dom.deleteCookie(i)}}else{a1portal.setVar("__a1portal_"+k+"_Visible","true")}}else{e.src=g;a1portal.dom.collapseElement(f,c);e.title=a1portal.getVar("max_text");if(i!=null){if(a1portal.getVar("__a1portal_"+k+":defminimized")=="true"){a1portal.dom.deleteCookie(i)}else{a1portal.dom.setCookie(i,"false",365)}}else{a1portal.setVar("__a1portal_"+k+"_Visible","false")}}return true}return false}function __a1portal_Help_OnClick(a){var b=a1portal.dom.getById(a);if(b!=null){if(b.style.display=="none"){b.style.display=""}else{b.style.display="none"}return true}return false}function __a1portal_SectionMaxMin(f,c){var d=a1portal.dom.getById(c);if(d!=null){var g=f.getAttribute("max_icon");var e=f.getAttribute("min_icon");var a=f.getAttribute("userctr")!=null;var b;if(d.style.display=="none"){f.src=e;d.style.display="";if(a){b="True"}else{a1portal.setVar(f.id+":exp",1)}}else{f.src=g;d.style.display="none";if(a){b="False"}else{a1portal.setVar(f.id+":exp",0)}}if(a){a1portalcore.setUserProp(f.getAttribute("userctr"),f.getAttribute("userkey"),b,null)}return true}return false}function __a1portal_enableDragDrop(){var b=a1portal.getVar("__a1portal_dragDrop").split(";");var f;a1portal.dom.positioning.disableSelectText();var e=b.length;for(var c=0;c<e;c++){f=b[c].split(" ");if(f[0].length>0){var a=a1portal.dom.getById(f[0]);var d=a1portal.dom.getById(f[1]);if(d.modulePaneId=="undefined"||d.modulePaneId==null){d.modulePaneId=f[3]}if(d.moveBlockId=="undefined"||d.moveBlockId==null){d.moveBlockId=f[4]}if(d.pagePaneId=="undefined"||d.pagePaneId==null){d.pagePaneId=f[5]}if(a!=null&&d!=null){a1portal.dom.positioning.enableDragAndDrop(a,d)}}}}var __a1portal_oPrevSelPane=null;var __a1portal_oPrevSelModule=null;var __a1portal_dragPlaceholderId="a1portal_drag_placeholder";var __a1portal_oPrevIIndex=null;var __a1portal_dragPlaceholder=null;function __a1portal_dragOver(){var k=a1portal.dom.positioning.dragCont;var n=__a1portal_getMostSelectedPane();if(n!=null){var j=__a1portal_getPaneControlIndex(k,n);if(__a1portal_oPrevSelPane!=null){if(__a1portal_oPrevSelPane.id!=n.id){__a1portal_oPrevSelPane.pane.style.border=__a1portal_oPrevSelPane.origBorder}else{if(__a1portal_oPrevIIndex==j&&__a1portal_oPrevSelModule!=null){return}}}a1portal.dom.positioning.removePlaceholder(__a1portal_dragPlaceholder,__a1portal_dragPlaceholderId);if(__a1portal_oPrevSelPane!=null){__a1portal_RecalculatePaneControlDims(__a1portal_oPrevSelPane)}__a1portal_RecalculatePaneControlDims(n);n.pane.style.outline="1px dotted "+A1PORTAL_HIGHLIGHT_COLOR;var d;var a;var h;var c;var e=n.controls.length;for(var g=0;g<e;g++){var f=n.controls[g].id;var b=n.controls[g];var l=k.id;if(j>g&&f!=l){d=b}if(j<=g&&f!=l){a=b;break}}if(__a1portal_dragPlaceholder==null){__a1portal_dragPlaceholder=document.createElement("div");__a1portal_dragPlaceholder.setAttribute("id",__a1portal_dragPlaceholderId);__a1portal_dragPlaceholder.setAttribute("class","moduleMovePlaceholder");__a1portal_dragPlaceholder.style.border="1px dotted red";__a1portal_dragPlaceholder.style.margin="4px 0px 4px 0px"}var m=a1portal.dom.positioning.elementWidth(n.pane);if(m!=null){if(m<=k.elementWidth){if(a!=null){m=document.id(a.control).getSize().x}else{if(d!=null){m=document.id(d.control).getSize().x}}__a1portal_dragPlaceholder.style.width=(m-2)+"px"}else{__a1portal_dragPlaceholder.style.width=""}}__a1portal_dragPlaceholder.style.height="50px";if(a!=null){__a1portal_oPrevSelModule=a;h=a1portal.dom.getNonTextNode(a.control);h.parentNode.insertBefore(__a1portal_dragPlaceholder,h)}else{if(d!=null){__a1portal_oPrevSelModule=d;h=a1portal.dom.getNonTextNode(d.control);a1portal.dom.insertAfter(__a1portal_dragPlaceholder,h)}else{h=a1portal.dom.getById(n.id);a1portal.dom.appendChild(h,__a1portal_dragPlaceholder)}}__a1portal_oPrevIIndex=j;__a1portal_RecalculatePaneDims()}}function __a1portal_dragComplete(){var d=a1portal.dom.positioning.dragCont;var f=d.getAttribute("moduleid");var c=d.getAttribute("moduleidentifier");var b=__a1portal_getMostSelectedPane();var g;if(b==null){var a=__a1portal_Panes();var h=a.length;for(var e=0;e<h;e++){if(a[e].id==d.parentNode.id){b=a[e]}}}if(b!=null){g=__a1portal_getPaneControlIndex(d,b);__a1portal_MoveToPane(b,d,g);a1portal.dom.positioning.destroyDragEvent();showPageLoader("");a1portal.callPostBack("MoveToPane","moduleid="+f,"moduleidentifier="+c,"pane="+b.paneName,"order="+g*2)}}function __a1portal_MoveToPane(b,c,f){if(b!=null){var e=new Array();var a=b.controls.length;for(var d=f;d<a;d++){if(b.controls[d].control.id!=c.id){e[e.length]=b.controls[d].control}a1portal.dom.removeChild(b.controls[d].control)}a1portal.dom.appendChild(b.pane,c);c.style.top=0;c.style.left=0;c.style.position="relative";var g=e.length;for(var d=0;d<g;d++){a1portal.dom.appendChild(b.pane,e[d])}__a1portal_RefreshPanes()}else{c.style.top=0;c.style.left=0;c.style.position="relative"}}function __a1portal_RefreshPanes(){var c=a1portal.getVar("__a1portal_Panes").split(";");var b=a1portal.getVar("__a1portal_PaneNames").split(";");__a1portal_m_aryPanes=new Array();var a=c.length;for(var d=0;d<a;d++){if(c[d].length>0){__a1portal_m_aryPanes[__a1portal_m_aryPanes.length]=new __a1portal_Pane(a1portal.dom.getById(c[d]),b[d])}}}var __a1portal_m_aryPanes;var __a1portal_m_aryModules;function __a1portal_Panes(){if(__a1portal_m_aryPanes==null){__a1portal_m_aryPanes=new Array();__a1portal_RefreshPanes()}return __a1portal_m_aryPanes}function __a1portal_RecalculatePaneDims(){var a=__a1portal_Panes();var d=a.length;for(var c=0;c<d;c++){var b=a[c];b.dims=a1portal.dom.positioning.dims2(b.pane)}}function __a1portal_RecalculatePaneControlDims(b){if(b!=null){var e=b.controls;var a=e.length;var c=null;for(var d=0;d<a;d++){c=e[d];c.dims=a1portal.dom.positioning.dims2(c.control)}}}function __a1portal_Modules(a){if(__a1portal_m_aryModules==null){__a1portal_RefreshPanes()}return __a1portal_m_aryModules[a]}function __a1portal_getMostSelectedPane(){var g=new Array();var j=a1portal.dom.event.object;var l=document.documentElement;var k=document.body;var d=j.pageX||(j.clientX+(l.scrollLeft||k.scrollLeft));var a=j.pageY||(j.clientY+(l.scrollTop||k.scrollTop));if(__a1portal_oPrevSelPane!=null){var c=__a1portal_oPrevSelPane.dims;if((d>c.l)&&(a>c.t)&&(d<(c.l+c.w))&&(a<(c.t+c.h))){g[0]=__a1portal_oPrevSelPane}}if(g.length==0){var h=__a1portal_Panes();var e=h.length;for(var f=0;f<e;f++){var m=h[f];var c=m.dims;if((d>c.l)&&(a>c.t)&&(d<(c.l+c.w))&&(a<(c.t+c.h))){g[g.length+1]=m}}}return g[g.length-1]}function __a1portal_getPaneControlIndex(f,b){if(b==null){return}var g=f.getOffsets().y;var c;if(b.controls.length==0){return 0}var a=b.controls.length;for(var d=0;d<a;d++){c=b.controls[d];var e=c.dims;if(c.control!=f&&g<e.t+50){return c.index}}if(c!=null){return c.index+1}else{return 0}}function __a1portal_getPaneControlIndex2(f,b){if(b==null){return}var h=f.getOffsets().y;var c;var a=b.controls.length;if(a==0){return 0}var e=0;for(e;e<a;e++){c=b.controls[e];if(c.control==f){break}}var d=null;var g=null;if(e==0){if(a>1){g=b.controls[1]}else{return 0}}else{if(e==a-1){d=b.controls[e-1]}else{d=b.controls[e-1];g=b.controls[e+1]}}if(d!=null&&h<d.dims.t){return d.index}if(g!=null&&h>g.dims.t){return g.index}}function __a1portal_Pane(b,c){this.pane=b;this.id=b.id;this.controls=new Array();this.origBorder=b.style.border;this.paneName=c;var g=0;var f="";var a=b.childNodes.length;for(var e=0;e<a;e++){var h=b.childNodes[e];if(a1portal.dom.isNonTextNode(h)){if(__a1portal_m_aryModules==null){__a1portal_m_aryModules=new Array()}var d=h.getAttribute("moduleid");if(d!=null&&d.length>0){f+=d+"~";this.controls[this.controls.length]=new __a1portal_PaneControl(h,g);__a1portal_m_aryModules[d]=h.id;g+=1}}}this.moduleOrder=f;this.dims=a1portal.dom.positioning.dims2(this.pane)}function __a1portal_PaneControl(a,b){this.control=a;this.id=a.id;this.index=b;this.origBorder=a.style.border;this.dims=a1portal.dom.positioning.dims2(this.control)}function __a1portalcore(){this.GetUserVal=0;this.SetUserVal=1}__a1portalcore.prototype={getUserProp:function(b,c,a){this._doUserCallBack(a1portalcore.GetUserVal,b,c,null,new a1portalcore.UserPropArgs(b,c,a))},setUserProp:function(c,d,a,b){this._doUserCallBack(a1portalcore.SetUserVal,c,d,a,new a1portalcore.UserPropArgs(c,d,b))},_doUserCallBack:function(c,d,e,a,b){if(a1portal&&a1portal.xmlhttp){var f=c+COL_DELIMITER+d+COL_DELIMITER+e+COL_DELIMITER+a;a1portal.xmlhttp.doCallBack("__Page",f,a1portalcore._callBackSuccess,b,a1portalcore._callBackFail,null,true,null,0)}else{alert("Client Personalization not enabled")}},_callBackSuccess:function(a,b,c){if(b.pFunc){b.pFunc(b.namingCtr,b.key,a)}},_callBackFail:function(a,b){window.status=a}};__a1portalcore.prototype.UserPropArgs=function(b,c,a){this.namingCtr=b;this.key=c;this.pFunc=a};var a1portalcore=new __a1portalcore();function __a1portal_GetRelatedTarget(a){if(!a){return null}return a.relatedTarget||(a.type=="mouseout"?a.toElement:a.fromElement)}function __a1portal_IsChildOf(a,b){if(b==a){return false}while(b&&(b!=document.body)){if(b==a){return true}try{b=b.parentNode}catch(c){return false}}return false}function __a1portal_isElementOver(b,a){var c=__a1portal_GetRelatedTarget(a);if(__a1portal_IsChildOf(b,c)||b==c){return true}return false}function __a1portal_isElementOut(b,a){var c=__a1portal_GetRelatedTarget(a);if(c&&(__a1portal_IsChildOf(b,c)||b==c)){return true}return false}function toggleDiv(a){if(document.getElementById(a).style.display=="none"){document.getElementById(a).style.display="block"}else{if(document.getElementById(a).style.display=="block"){document.getElementById(a).style.display="none"}}};