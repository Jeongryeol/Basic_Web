<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>
Google Map 추가하기
</title>

<!-- ―――[[ import ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->

	<!-- Google Map API import code -->
	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCoiFcL5pbT3d5Oe5rLCQwTB14i6acEkQs"></script>
		<!-- Google Map APU Documnet Link
		https://developers.google.com/maps/documentation/javascript/tutorial -->
	
	<!-- jQuery script import code :: $-undifined error 주의!!! -->
	<script type="text/javascript" src="/js/jquery-1.12.0.js"></script>
	
<!-- ―――[[HEAD script]]――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<script type="text/javascript">
	var gmap; //지도
	var myPosition = { //나의 현재위치
			 latitude:37.478710
			,longitude:126.878700
	}
	/* 현재 나의 위치를 얻는 함수 */
	function getMyLocation(){
		//브라우저가 지오로케이션 API를 지원하는 여부를 검사하는 부분
		//navigator.geolocation객체가 존재하면 지원하는 것이다.
		if(navigator.geolocation) {//지원하고 있다면..
			console.log("지오 로케이션이 제공됩니다.");
			//navigator.geolocation속성은 지오로케이션 API전체를 포함하는 객체이다.
			//이 API의 메인 메소드가 브라우저의 위치를 가져오는 일을 하는 getCurrentPostion()함수
			//지원하고 있다면 getCurrentPosition메소드를 호출해서 displayLocation이란 핸들러함수를 호출함
			navigator.geolocation.getCurrentPosition(displayLocation);
		}else{
			console.log("이런.. 지오 로케이션이 제공되지 않습니다.");
		}
	}
	function displayLocation(position){
		console.log('displayLocation() 호출성공');
		var latitude = position.coords.latitude;
		var longitude = position.coords.longitude;
		$("#location").text("당신의 위도 : "+latitude+" / 당신의 경도 : "+longitude);
		showMap(position.coords);
	}
	function showMap(coords){
		//지도위치(위도,경도)
		var latNlong = new google.maps.LatLng(coords.latitude, coords.longitude);
		console.log("latNlong : "+latNlong);//콘솔확인용
		//지도옵션
		var mapOptions={
				 zoom : 16 		//기본 줌
				,maxZoom : 20 	//최대 줌
			    ,minZoom : 8 	//최소 줌
				,center:latNlong//지도중심위치
				,disableDefaultUI : true 		//기본 UI
				,disableDoubleClickZoom : true 	//더블클릭 지점 중심으로 지도 확대기능 사용여부
				,draggable : true 				//지도 드래그이동기응 사용여부
				,keyboardShortcuts : true 		//키보드단축키 사용여부
				,mapTypeId:google.maps.MapTypeId.ROADMAP//SATELITE(위성)과 HYBRID
		};
		//지도생성 : (<div>태그에 지도를 위치시킬때,) 나의 위치로 표시하기
	//	gmap = new google.maps.Map($("#location")[0],mapOptions);					//jQuery
		gmap = new google.maps.Map(document.getElementById("location"),mapOptions);	//표준 스크립트
			//자바스크립트에서는 DOM에 접근할 수 있다.
			//이 DOM에 접근할 때 배열표현식으로 사용할 수 있다.
			//같은 이름이 여러개가 되면 자동으로 배열로 전환된다.(원칙상 1개짜리는 배열로 전환되지않음)
		console.log("map : "+gmap);//테스트용 콘솔출력
		
		//지도 내 마커에 말풍선 추가
		var markerTitle = "'박의'가 추천하는 맛집";	//마커제목 객체
		var parklatNlong = new google.maps.LatLng(37.480430, 126.879001); //마커위치정보 객체
		var marker = new google.maps.Marker({ 	//마커생성 및 옵션추가
			 position : parklatNlong 			//표시위치 : 위치정보입력
			,map : gmap 						//표시지도 : 지도객체 입력
			,title : markerTitle 				//표시마커제목 : 마커제목객체 입력 
		});
		var markerMaxWidth = 300;	//마커를 클릭했을 때 나타나는 말풍선의 최대 크기
		var mcontent = "<div>";		//말풍선의 내용
			mcontent+= "<h2>"+markerTitle+"</h2><p>맛있는 메뉴는 떡볶이와 돈까스에요 . 떡볶이는 밀떡을 써서 훨씬 쫄깃하고요. 라면과 쫄면은 불기전에 먹어야해요 그리고 돈까스는 얇고 바삭하죠 볶음밥을 먹을때는 튀김만두와 계란을 남겨서 같이 볶아드셔야 맛이 훨씬 좋아요~^^ 그리고 3인 이상이면 콜라가 서비스☆</p>";
			mcontent+= "</div>"
		var infoWin = new google.maps.InfoWindow({
			 content : mcontent 		//구글제공API의 마커컨텐츠로 설정
			,maxWidth : markerMaxWidth 	//마커최대길이
		});
		
		//마커 이벤트처리 : 말풍선 열기
		google.maps.event.addListener(marker,'click',function(){//지도상에서 지정 마커 클릭시
			infoWin.open(gmap,marker);							//지정 말풍선팝업 표시하기	
		});
		//마커 이벤트처리 : 말풍선 닫기
		google.maps.event.addListener(marker,'click',function(){//지도상에서 지정 마커 클릭시
			infoWin.close();									//지정 말풍선팝업 닫기	
		});
		console.log("gmap : "+gmap);
	}
	function foodInfoSearch(){
		$.ajax({
			 url : "./jsonFood.jsp"
			,method : "GET"
		//	,dataType : "json"
			,success : function(json){
				$("#d_foodInfo").text(json);
			}
			,error : function(xhrObject){
				alert(xhrObject.responseText);
			}
		});
	}
</script>
</head>
<!--
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-->
<body onload="getMyLocation()">
<!-- ―――[[BODY script]]――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<script type="text/javascript">

</script>

<!-- ―――[[BODY content]]――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<p>
	<!-- 검색창 +검색 버튼 추가하기 -->
	<input type="text" id="address" width="300px" value="월드메르디앙2차">
	<input type="button" id="btn_search" value="장소검색" onClick="foodInfoSearch()">
</p>
	<!-- 구글지도 추가하기 -->
<div id="location" style="width:800px;height:600px;">
	<!-- INSERT YOUR LOCATION IN GOOGLE MAP HERE -->
</div>

<div id="d_foodInfo">
<!-- 위치정보 가져왔는지 확인하기위한 테스트코드 -->
</div>

</body>
</html>