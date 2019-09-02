window.addEventListener("load",function(){
    //영역
    var section = this.document.querySelector("#notice");
    //처음페이지 값.
    var pageLoaded = 1;
    //버튼이 여러개 -> 부모에게 걸어준다
    var noticeLists = section.querySelector(".notice-lists");
    
    window.addEventListener("scroll", function(e){
        var pageHeight=document.documentElement.offsetHeight,
        windowHeight=window.innerHeight,
        scrollPosition=window.scrollY || window.pageYOffset || document.body.scrollTop + (document.documentElement && document.documentElement.scrollTop || 0);
        
        //var x = 3 || 0;
        //alert(x);
        if (pageHeight <= windowHeight+scrollPosition+200) {
            //alert('At the bottom');
            
            //var num = morepage.value;
            var request = new XMLHttpRequest();
            request.addEventListener("load" ,function() {
                //버튼이 여러개 -> 부모에게 걸어준다
                
                var json = JSON.parse(request.responseText);
                console.log(request.responseText);
                //alert(json);
                
                var template = document.querySelector("main .notice-template");
                //body.innerHTML = ""; //다 지우기
                for (var i = 0; i < json.length; i++) {
                    var cloneTr = document.importNode(template.content, true);  
                    var categoryEl = cloneTr.querySelector(".categoryName");
                    var writerEl = cloneTr.querySelector(".writerId");
                    var regdateEl = cloneTr.querySelector(".regDate");
                    var titleEl = cloneTr.querySelector(".title");
                    var contentEl = cloneTr.querySelector(".content");
                    categoryEl.innerText = json[i].categoryName;
                    writerEl.innerText = json[i].writerId;
                    regdateEl.innerText = json[i].regDate;
                    titleEl.innerText = json[i].title;
                    contentEl.innerText = json[i].content;         
                    console.log(json[i].id);
                    
                    notice.append(cloneTr);
                }
                
                
                var noticeLists = document.querySelectorAll(".notice-template-section");
                
                for(var i =0;  i < noticeLists.length; i++){
                    noticeLists[i].onclick =function(e){
                        
                        var contentDiv = e.target.parentElement.nextElementSibling;
                        // console.log("00"+e.target);
                        // console.log("00"+e.target.className);
                        contentDiv.classList.add("current");
                    }
                }
                
                
            });
            request.open("GET", "list-json?p=" + (++pageLoaded));
            request.send();
        }
    });
    //notice 내용 자세히보기
    noticeLists.onclick = function(e){
        var contentDiv = e.target.parentElement.nextElementSibling;
        console.log("00"+e.target);
        console.log("00"+e.target.className);
        contentDiv.classList.toggle("current");
    };
});