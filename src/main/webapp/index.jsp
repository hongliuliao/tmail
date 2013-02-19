<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello world!</title>
<script type="text/javascript" src="js/common/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/common/json2.js"></script>
<script type="text/javascript" src="js/common/jquery.cookie.js"></script>
<script type="text/javascript" src="js/common/jquery.jsoncookie.js"></script>
<script type="text/javascript" src="js/common/mustache.js"></script>

<script type="text/javascript" src="js/mail.js"></script>
<script type="text/javascript" src="js/account.js"></script>
<script type="text/javascript" src="js/talk.js"></script>
<script type="text/html" id="talkdialogTemplate">
	<div id="appWindow_3" class="window window_current"
		style="width: 445px; height: 450px; left: 441px; top: 153px; display: block; visibility: visible; z-index: 21;">
		<div id="window_outer_3" class="window_outer eqq_window"
			style="padding: 10px; height: 430px; z-index: 16;">
			<div id="window_inner_3" class="window_inner" style="z-index: 21;">
				<div class="window_bg_container" id="window_bg_container_3"
					style="background-image: url(http://0.web.qstatic.com/webqqpic/pubapps/0/50/images/bg.png); background-color: rgb(244, 249, 252); background-position: initial initial; background-repeat: repeat no-repeat;">
				</div>
				<div class="window_content">
					<div id="window_titleBar_3" class="window_titleBar"
						style="height: 83px;">
						<div id="window_titleButtonBar_3" class="window_titleButtonBar">
							<a id="ui_button_8"
								class="ui_button window_action_button window_close" title="关闭"
								hidefocus="" href="###" style="display: block;"></a><a
								id="ui_button_9"
								class="ui_button window_action_button window_max" title="最大化"
								hidefocus="" href="###" style="display: block;"></a><a
								id="ui_button_10"
								class="ui_button window_action_button window_restore" title="还原"
								hidefocus="" href="###" _olddisplay="" style="display: none;"></a><a
								id="ui_button_11"
								class="ui_button window_action_button window_min" title="最小化"
								hidefocus="" href="###" style="display: block;"></a><a
								id="ui_button_12"
								class="ui_button window_action_button window_fullscreen"
								title="全屏" hidefocus="" href="###" _olddisplay=""
								style="display: none;"></a><a id="ui_button_13"
								class="ui_button window_action_button window_restore_full"
								title="退出全屏" hidefocus="" href="###" _olddisplay=""
								style="display: none;"></a>
						</div>
						<div id="window_title_3" class="window_title titleText"
							style="height: 83px;">
							<div id="chatBox_nameArea_3547035257" class="chatBox_nameArea">
								<a id="chatBox_allName_3547035257"
									class="chatBox_allName titleText" uin="3547035257"
									title="尤春(尤春) " href="###"> <span class="chatBox_mainName"
									id="chatBox_mainName_3547035257">{{talkto}}</span>
								</a>
							</div>
						</div>
					</div>
					<div id="window_body_outer_3" class="window_bodyOuter"
						style="width: 425px; top: 83px; height: 347px;">
						<div id="window_toolBar_3" class="window_toolBar"></div>
						<div id="window_toggleToolbar_3"
							class="app_toolbar_icon app_toolbar_toggle app_toolbar_toggle_up"
							style="display: none"></div>
						<div id="window_body_3" class="window_bodyArea"
							style="width: 425px; height: 347px;">
							<div id="chatBox_sideBar2_3547035257" class="chatBox_sideBar2"
								_olddisplay="block" style="display: none;"></div>
							<div id="chatBox_sideBar_3547035257" class="chatBox_sideBar">
							</div>
							<div class="chatBox_mainArea"
								id="chatBox_chatBox_mainArea_3547035257">
								<div id="chatBox_chatBoard_3547035257" class="chatBox_chatBoard">
									<div id="chatBox_3547035257_moreMsgTip"
										class="chatBox_moreMsgTip">
										<span class="tipIcon"></span>消息记录未全部展示，<a
											id="chatBox_3547035257_moreMsgTipBtn" href="###"
											class="linkBtn">查看更多</a>
									</div>
									<div id="chatBox_msgList_3547035257" class="chatBox_msgList"></div>
									<a href="#" onclick="return false;" title="回到底部"><div
											id="chatBox_msgList_backToBottom_3547035257"
											class="chatBox_msgList_backToBottom"></div></a>
									<div id="chatBox_yellowTipsBar_3547035257"
										class="chatBox_yellowTipsBar"></div>
								</div>
								<div id="chatBox_fontToolBar_3547035257" class="editorToolbar"
									unselectable="on">
									<ul class="toolbar" unselectable="on">
										<li><select
											id="chatBox_fontToolBar_3547035257_fontFamily"
											class="fontFamily"><option value="宋体">宋体</option>
												<option value="黑体">黑体</option>
												<option value="隶书">隶书</option>
												<option value="微软雅黑">微软雅黑</option>
												<option value="楷体_GB2312">楷体_GB2312</option>
												<option value="幼圆">幼圆</option>
												<option value="Arial">Arial</option>
												<option value="Arial Black">Arial Black</option>
												<option value="Times New Roman">Times New Roman</option>
												<option value="Verdana">Verdana</option></select></li>
										<li><select id="chatBox_fontToolBar_3547035257_fontSize"
											class="fontSize"><option value="8">8</option>
												<option value="9">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option></select></li>
										<li><a id="chatBox_fontToolBar_3547035257_bold" href="#"
											class="icon" title="粗体"><span class="bold"></span></a></li>
										<li><a id="chatBox_fontToolBar_3547035257_italic"
											href="#" class="icon" title="斜体"><span class="italic"></span></a></li>
										<li><a id="chatBox_fontToolBar_3547035257_underline"
											href="#" class="icon" title="下划线"><span class="underline"></span></a></li>
										<li><a id="chatBox_fontToolBar_3547035257_color" href="#"
											class="icon" title="颜色"><span class="color"></span></a></li>
									</ul>
									<ul id="chatBox_fontToolBar_3547035257_colorPanel"
										class="colorPanel" _olddisplay="" style="display: none;">
										<li><a href="#"><span style="background: #000000"></span></a></li>
										<li><a href="#"><span style="background: #993300"></span></a></li>
										<li><a href="#"><span style="background: #333300"></span></a></li>
										<li><a href="#"><span style="background: #003300"></span></a></li>
										<li><a href="#"><span style="background: #003366"></span></a></li>
										<li><a href="#"><span style="background: #000080"></span></a></li>
										<li><a href="#"><span style="background: #333399"></span></a></li>
										<li><a href="#"><span style="background: #333333"></span></a></li>
										<li><a href="#"><span style="background: #800000"></span></a></li>
										<li><a href="#"><span style="background: #FF6600"></span></a></li>
										<li><a href="#"><span style="background: #808000"></span></a></li>
										<li><a href="#"><span style="background: #008000"></span></a></li>
										<li><a href="#"><span style="background: #008080"></span></a></li>
										<li><a href="#"><span style="background: #0000FF"></span></a></li>
										<li><a href="#"><span style="background: #666699"></span></a></li>
										<li><a href="#"><span style="background: #808080"></span></a></li>
										<li><a href="#"><span style="background: #FF0000"></span></a></li>
										<li><a href="#"><span style="background: #FF9900"></span></a></li>
										<li><a href="#"><span style="background: #99CC00"></span></a></li>
										<li><a href="#"><span style="background: #339966"></span></a></li>
										<li><a href="#"><span style="background: #33CCCC"></span></a></li>
										<li><a href="#"><span style="background: #3366FF"></span></a></li>
										<li><a href="#"><span style="background: #800080"></span></a></li>
										<li><a href="#"><span style="background: #999999"></span></a></li>
										<li><a href="#"><span style="background: #FF00FF"></span></a></li>
										<li><a href="#"><span style="background: #FFCC00"></span></a></li>
										<li><a href="#"><span style="background: #FFFF00"></span></a></li>
										<li><a href="#"><span style="background: #00FFFF"></span></a></li>
										<li><a href="#"><span style="background: #00FFFF"></span></a></li>
										<li><a href="#"><span style="background: #00CCFF"></span></a></li>
										<li><a href="#"><span style="background: #993366"></span></a></li>
										<li><a href="#"><span style="background: #C0C0C0"></span></a></li>
										<li><a href="#"><span style="background: #FF99CC"></span></a></li>
										<li><a href="#"><span style="background: #FFCC99"></span></a></li>
										<li><a href="#"><span style="background: #FFFF99"></span></a></li>
										<li><a href="#"><span style="background: #CCFFCC"></span></a></li>
										<li><a href="#"><span style="background: #CCFFFF"></span></a></li>
										<li><a href="#"><span style="background: #99CCFF"></span></a></li>
										<li><a href="#"><span style="background: #CC99FF"></span></a></li>
										<li><a href="#"><span style="background: #FFFFFF"></span></a></li>
									</ul>
								</div>
								<div id="chatBox_toolBar_top_3547035257"
									class="chatBox_toolBar_top">pull me</div>
								<div id="chatBox_toolBar_3547035257" class="chatBox_toolBar">
									<a id="chatBox_fontButton_3547035257" href="###"><div
											class="chatBox_fontButton" title="设置字体颜色和格式"></div></a> <a
										href="###"><div id="chatBox_faceButton_3547035257"
											class="chatBox_faceButton" title="表情"></div></a> <a
										id="chatBox_handwriteButton_3547035257" href="###"><div
											class="chatBox_handwriteButton" title="QQ云手写板"></div></a> <a
										href="###" id="chatBox_shakeButton_3547035257"><div
											class="chatBox_shakeButton" title="向好友发送窗口抖动"></div></a>
									<iframe id="uploadFilIframe_3547035257"
										name="uploadFilIframe_3547035257" style="display: none"
										src="./domain.html"></iframe>
									<a href="###" id="chatBox_sendPicButton_wrap_3547035257"><form
											id="uploadSendPicfile_3547035257"
											name="uploadSendPicfile_3547035257" title="发送图片..."
											class="sendPicForm" target="uploadFilIframe_3547035257"
											action="" method="POST" enctype="multipart/form-data">
											<div id="chatBox_sendPicButton_3547035257"
												class="chatBox_sendPicButton" title="发送图片...">
												<input name="callback" type="hidden"
													value="parent.EQQ.Model.ChatMsg.callbackSendPic"> <input
													name="locallangid" type="hidden" value="2052"> <input
													name="clientversion" type="hidden" value="1409"> <input
													name="uin" type="hidden" value="3547035257"> <input
													name="skey" type="hidden" value="@325fz2vag"> <input
													name="appid" type="hidden" value="1002101"> <input
													name="peeruin" type="hidden" value="593023668"> <input
													id="offline_pic_3547035257" class="f" name="file"
													type="file"> <input name="fileid" type="hidden"
													value=""> <input name="vfwebqq" type="hidden"
													value=""> <input name="senderviplevel"
													type="hidden" value=""> <input
													name="reciverviplevel" type="hidden" value="">
											</div>
										</form></a> <a id="chatBox_snapButton_3547035257" href="###"><div
											class="chatBox_snapButton" title="截屏"></div></a> <a
										id="chatBox_clearButton_3547035257" href="###"><div
											class="chatBox_clearButton" title="清屏"></div></a> <a href="###"
										_olddisplay="block" style="display: none;"><div
											id="chatBox_maskButton_3547035257"
											class="chatBox_acceptButton" title="群屏蔽"></div></a> <a
										id="chatBox_chatLogButton_3547035257"
										class="chatBox_historyButtonCon" title="消息记录" href="###"><div
											class="chatBox_historyButton"></div>
										<div class="chatBox_historyButtontxt">消息记录</div>
										<div class="chatBox_Down"></div></a>
								</div>
								<div id="chatBox_inputBox_3547035257" class="chatBox_inputBox">
									<input type='text' name='mailTitle' class="inputTitle"/>
									<div class="rich_editor"
										style="font-family: 宋体; font-size: 10pt; font-weight: normal; font-style: normal; text-decoration: initial; color: rgb(0, 0, 0);">
										<div class="rich_editor_div" style="display: block;"
											contenteditable="true">
											<br>
										</div>
										<textarea class="rich_editor_text" _olddisplay="block"
											style="display: none;"></textarea>
									</div>
								</div>
								<div class="chatBox_controlPanel">
									<input id="chatBox_speechButton_3547035257"
										class="chatBox_speechButton" title="语音输入" x-webkit-speech=""
										style="display: block;"> <a href="###"
										id="chatBox_sendOptionButton_3547035257"
										class="chatBox_sendOptionButton" title="修改发送快捷键"></a> <a
										href="###" id="chatBox_sendMsgButton_3547035257"
										class="chatBox_sendMsgButton" title="发送">发 送</a> <a href="###"
										id="chatBox_closeButton_3547035257"
										class="chatBox_closeButton" title="关闭">关 闭</a>
								</div>
							</div>
						</div>
					</div>
					<div id="window_controlArea_3" class="window_controlArea"
						_olddisplay="" style="display: none;"></div>
				</div>
				<div id="window_1_resize_t"
					style="position: absolute; overflow: hidden; background-image: url(http://0.web.qstatic.com/webqqpic/js/assets/images/transparent.gif); cursor: n-resize; z-index: 1; left: 0px; top: -5px; width: 100%; height: 5px; display: block; background-position: initial initial; background-repeat: initial initial;"></div>
				<div id="window_1_resize_r"
					style="position: absolute; overflow: hidden; background-image: url(http://0.web.qstatic.com/webqqpic/js/assets/images/transparent.gif); cursor: e-resize; z-index: 1; right: -5px; top: 0px; width: 5px; height: 100%; display: block; background-position: initial initial; background-repeat: initial initial;"></div>
				<div id="window_1_resize_b"
					style="position: absolute; overflow: hidden; background-image: url(http://0.web.qstatic.com/webqqpic/js/assets/images/transparent.gif); cursor: s-resize; z-index: 1; left: 0px; bottom: -5px; width: 100%; height: 5px; display: block; background-position: initial initial; background-repeat: initial initial;"></div>
				<div id="window_1_resize_l"
					style="position: absolute; overflow: hidden; background-image: url(http://0.web.qstatic.com/webqqpic/js/assets/images/transparent.gif); cursor: w-resize; z-index: 1; left: -5px; top: 0px; width: 5px; height: 100%; display: block; background-position: initial initial; background-repeat: initial initial;"></div>
				<div id="window_1_resize_rt"
					style="position: absolute; overflow: hidden; background-image: url(http://0.web.qstatic.com/webqqpic/js/assets/images/transparent.gif); cursor: ne-resize; z-index: 2; right: -5px; top: -5px; width: 5px; height: 5px; display: block; background-position: initial initial; background-repeat: initial initial;"></div>
				<div id="window_1_resize_rb"
					style="position: absolute; overflow: hidden; background-image: url(http://0.web.qstatic.com/webqqpic/js/assets/images/transparent.gif); cursor: se-resize; z-index: 2; right: -5px; bottom: -5px; width: 5px; height: 5px; display: block; background-position: initial initial; background-repeat: initial initial;"></div>
				<div id="window_1_resize_lb"
					style="position: absolute; overflow: hidden; background-image: url(http://0.web.qstatic.com/webqqpic/js/assets/images/transparent.gif); cursor: sw-resize; z-index: 2; left: -5px; bottom: -5px; width: 5px; height: 5px; display: block; background-position: initial initial; background-repeat: initial initial;"></div>
				<div id="window_1_resize_lt"
					style="position: absolute; overflow: hidden; background-image: url(http://0.web.qstatic.com/webqqpic/js/assets/images/transparent.gif); cursor: nw-resize; z-index: 2; left: -5px; top: -5px; width: 5px; height: 5px; display: block; background-position: initial initial; background-repeat: initial initial;"></div>
			</div>
		</div>
	</div>
</script>

<link rel="stylesheet" type="text/css" href="http://0.web.qstatic.com/webqqpic/pubapps/0/50/style.css?20121029001" />
<link rel="stylesheet" type="text/css" href="http://0.web.qstatic.com/webqqpic/style/qqweb.main.2.css?t=20121029001" />
<link rel="stylesheet" type="text/css" href="http://0.web.qstatic.com/webqqpic/style/jet.all.css?t=20120607001" />
<link rel="stylesheet" type="text/css" href="http://0.web.qstatic.com/webqqpic/style/qqweb.main.css?t=20121210001" />

<link rel="stylesheet" type="text/css" href="css/talk.css" />
</head>
<body>
	
	<a href='pages/desc.jsp'>应用简介</a>
	<form id='loginForm'>
		email:<input id='accountEmail' type='text' name="email" value="" />
		password:<input id='accountPassword' name="password" type='password' />
		<input id='addAccountBtn' type='button' value="确定" >
		<select id='accountHistorySel'></select>
	</form>
	<div id='accountInfo'></div>
	
	Command:<input id='command' type='text' value="list" />
	<input id='sendCommandBtn' type='button' value='确定' />
	<input id='listBtn' type='button' value='list' />
	<input id='removeBtn' type='button' value='remove' />
	<div id='processArea'></div>
	
	<br />
	<div id="responseArea" style="width=40;rows=20"></div><br />
	<hr />
	收件人:<input id='toText' type='text' /><br />
	主题:<input id='subjectText' type='text' /><br />
	内容:<textarea id="contextTextArea" cols="40" rows="10"></textarea><br />
	<input id='sendMailBtn' type='button' value="确定" />
	
</body>
</html>