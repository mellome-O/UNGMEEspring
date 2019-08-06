//프로필 변경 이벤트
window.addEventListener("load", function() {
	var profile = this.document.querySelector("#profile");
	var triggerBtn = profile.querySelector("input[type=button]");
	var fileBtn = profile.querySelector("input[name=file]");
	var poto = profile.querySelector(".profile");
	var token = this.document.querySelector("input[name=token]").value;
	var header = this.document.querySelector("input[name=header]").value;

	triggerBtn.onclick = function() {

		var event = new MouseEvent("click", {
			view : window,
			bubbles : true,
			cancelable : true
		});
		fileBtn.dispatchEvent(event);
		
//		submitBtn.onclick = function(){
//			var f1 = fileBtn1.files[0];
//			var f2 = fileBtn2.files[0];
//			
//			var formData = new FormData();
//			formData.append("file", f1);
//			formData.append("file", f2);
//		}

		fileBtn.onchange = function() {
			var file = fileBtn.files[0];
			var formData = new FormData();
			formData.append("file", file);
			
			// alert("upload?" + scrf.value);
			// alert(token);
			// alert(header);
			var request = new XMLHttpRequest();

			request.addEventListener("load", function() {
				//이미지 비동기 이루엄
				/* ============================================ */
				var reader = new FileReader();
				//온로드 < 이벤트
				//function() < 이벤트 함수
				//evt < 이벤트 객체 (필요에 따라서 쓰일수있다.)
				reader.onload = function(evt) {
					poto.src = evt.target.result;
					alert(request.responseText);
				}
				//리더기 역할을 하게된다 시점을 모르게된다 읽는 시점
				reader.readAsDataURL(file);

				//--- 로컬파일 읽기 요청-------------------------

			});
			request.open("post", "upload");
			request.setRequestHeader(header,token);
			request.send(formData);
		};
	};
});

//이름변경 이벤트
window.addEventListener("load", function() {
	var nickNameDiv = this.document.querySelector("#nickname");
	var nickName = nickNameDiv.querySelector("input[type=text]");
	var nickNameBtn = nickNameDiv.querySelector(".eraser");

	nickNameBtn.onclick = function() {
		//alert("들어옴");
		//alert(nickName.value);
		//rquest 가 로드될때 실행해 달라고 한것임
		var request = new XMLHttpRequest();
		request.addEventListener("load", function() {
			alert(request.responseText);
		})
		request.open("GET", "nickname?nickName=" + nickName.value);
		request.send();
	};
});

//비밀번호 변경 이벤트
window.addEventListener("load", function() {
	var change = this.document.querySelector("#change");
	var select = change.querySelector(".chage-button");
	var div = select.nextElementSibling;//노드선택
	var pw = change.querySelector(".pw-button");
	var pwEquel = change.querySelector(".pw-button1");
	var error = change.querySelector(".error");
	var changeBtn = change.querySelector("input[type=button]");
	var token = this.document.querySelector("input[name=token]").value;
	var header = this.document.querySelector("input[name=header]").value;
	var isValide = false;
	// var div = select.parentElement.nextSibling;
	this.console.log(select.nodeType);

	select.onclick = function() {
		div.classList.add("current");
		
		pwEquel.oninput = function() {
			console.log(pw.value);
			if (pw.value != pwEquel.value) {
				error.classList.remove("reverse")
				error.innerText = "비밀번호가 일치하지 않습니다."
				isValide = false;
			} else {
				error.classList.add("reverse")
				error.innerText = "일치합니다"
				isValide = true;
			}
		}
		changeBtn.onclick = function(e){
			//비밀번호 틀리면 넘기지못하게하는것
			
			if(!isValide){
				e.preventDefault();
				alert("비밀번호가 일치하지 않습니다.");
				return;
			}
			
			var pwd = pw.value;
			var formData = new FormData();
			formData.append("pwd",pwd)	
			
			var request = new XMLHttpRequest();
			request.addEventListener("load",function(){
				alert(request.responseText);
				//변경후 text박스 초기화 
				pw.value="";
				pwEquel.value="";
				div.classList.remove("current");
			});
			request.open("post","password/update");
			request.setRequestHeader(header,token);
			request.send(formData);
		}
	}
});
//커플 신청 이벤트
window.addEventListener("load",function(){
	var section = this.document.querySelector("#couple-state");
	var proposeBtn = section.querySelector(".propose-button");
	var proposePage = section.querySelector("#propose-page");
	var couplename = section.querySelector("input[name=name]");
	var email = section.querySelector("input[name=email]");
	var date = section.querySelector("intput[name=date]");
	var message = section.querySelector("input[name=message]");
	var token = this.document.querySelector("input[name=token]").value;
	var header = this.document.querySelector("input[name=header]").value;
	var proposeData = this.document.querySelector(".propose-data");
	var proposeDatas= this.document.forms["propose-datas"];
	proposeBtn.onclick = function(){
		if(proposePage.className == 'd-none')
			proposePage.classList.add("current");
		else
			proposePage.classList.remove("current");
	}
	//var formData = new FormData(proposeData);
	
	proposeData.onsubmit = function(e){
		e.preventDefault();
		console.log(typeof proposeDatas);
		console.log(proposeDatas.name.value)
		alert(token);
		alert(header);
		alert("gkdl");
		var request = new XMLHttpRequest();
		request.open("post","propose");
		request.setRequestHeader(header,token);
		request.send(proposeDatas);

	}
	
});
	//이벤트동의변경 이벤트
window.addEventListener("load", function() {
	var eventDiv = this.document.querySelector("#event-check");
	var changBtn = eventDiv.querySelector("input[type=button]");
	var eState = eventDiv.querySelector("input[type=hidden]").value;
	var eventState = eventDiv.querySelector(".event-state");
	changBtn.onclick = function() {
	
		if(eState =='F')
			eState = "T";
		else
			eState = "F";
			
		var request = new XMLHttpRequest();
		request.addEventListener("load", function() {
			var xhr = new XMLHttpRequest();
			xhr.addEventListener("load",function(){
				//alert("rksmd");
				//alert(xhr.responseText);
				var json = JSON.parse(xhr.responseText);
				if(json.echeck =='F')
				eventState.innerText='비동의';
				else
				eventState.innerText='동의';
				alert('변경되었습니다.');
			});
			xhr.open("get","event/get");
			xhr.send();
		})
		request.open("GET", "event/update?e=" + eState);
		request.send();
	};
});
//연습 소켓 
/*
window.addEventListener("load",function(){
	var cState = this.document.querySelector("#couple-state");
	var proposeBtn = cState.querySelector("input[name=propose]");

	proposeBtn.onclick = function(e){
		e.preventDefault();
	
		var ws = new WebSocket("ws://localhost:8080/ungmeespring/user/detail");

		ws.onopen = function(){
			console.log("info : connection opend");
			setTimeout(function(){connection();},1000)
			//커넥션후에 들어오는 게 원칙
			ws.onmessage = function(event){
				console.log(event.data+'\n');
			}
	
		}
		//눈에 보이긴 쉽지만 커넥션이 되지도않았는데 메세지를 받을수 없다.
		// ws.onmessage = function(event){
		// 	console.log(event.data+'\n');
		// }

		ws.onclose = function(event) {console.log('info.closed')};
		ws.onerror = function(err) {console.log('info error',err)};
	}
});
*/