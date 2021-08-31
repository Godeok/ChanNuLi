
# 찬누리

## 📖 목차
[1. 프로젝트 설명](#1-프로젝트-설명)<br>
[2. 제작 기간 & 참여 인원](https://github.com/Godeok/ChanNuLi/blob/master/README.md#2-%EC%A0%9C%EC%9E%91-%EA%B8%B0%EA%B0%84--%EC%B0%B8%EC%97%AC-%EC%9D%B8%EC%9B%90)<br>
[3. 사용한 기술](#3-사용한-기술)<br>
[4. 프로젝트 설계](#4-프로젝트-설계)<br>
[5. 기타](#5-기타)  <br/><br/><br/>

## 1. 프로젝트 설명
### 💊 어플 이름
<div align="center">
  <h3>찬누리</h3>
<img src="https://user-images.githubusercontent.com/40076944/131518766-374ec45f-b2a7-4b9a-9618-6b07f12afec5.png" width="200" height="200">
</div>

### ✨ 어플 소개 및 목적
찬누리는 **노인 건강 관리 어플**입니다. 노인분들은 찬누리를 사용하면서 스스로 건강 상태를 진단하고, 다양한 질병과 건강 정보를 얻고, 간단한 게임을 통해 치매도 예방할 수 있습니다. 또한, 찬누리는 노인분들이 어플을 쉽게 사용하실 수 있도록 간편한 화면 구성과 조작 방법을 제공합니다.

### 🛠 어플 기능 소개
찬누리 어플의 주요 기능으로는 자가 진단, 질병/건강 정보, 치매 예방 게임이 있습니다. 
<div align="center">
<img src="https://user-images.githubusercontent.com/40076944/131536575-9c01337b-6bb6-431b-b1a9-cef0d5df9dad.png" alt="drawing" width="300"/>
</div>
<br>

#### 1. 자가 진단하기
<div align="center">
<img src="https://user-images.githubusercontent.com/40076944/131531526-0cdd8940-5cd0-4e22-952d-5747b310e4fa.png" alt="drawing" width="700"/>
</div>
<br>
65세 이상 노인에게 나타나는 대표적인 7 종류의 질병을 사용자 스스로 진단하고 예방합니다.<br>
자가 진단 후 결과를 저장하고 결과 확인 페이지에서 진단을 한 날짜 순이나 질병 종류 별로 결과를 확인할 수 있습니다.<br>
- 자가 진단 종류: 고혈압, 골관절염, 고지혈증, 요통, 당뇨병, 골다공증, 치매
<br><br>

#### 2. 질병/건강 정보 보기
<div align="center">
<img src="https://user-images.githubusercontent.com/40076944/131533748-c6226c77-ae74-46a8-a641-83e90b328019.png" alt="drawing" width="700"/>
</div>
<br>
대표적인 노인 질병 정보와 건강 관리에 필요한 정보를 직접 검색하고 확인할 수 있습니다.<br>
노인분들이 검색 엔진을 사용할 때 검색 키워드 선택의 어려움을 줄이고자 건강 관리 추천 검색 키워드를 제공합니다. 추가로, 노인분들께 추천하는 건강 관리 영상 또한 볼 수 있습니다.<br>
- 제공하는 질병 정보 종류: 고혈압, 골관절염, 류마티스 관절염, 고지혈증, 요통, 좌골신경통, 당뇨병, 골다공증<br><br>
- 제공하는 건강 관리 추천 검색 키워드 : 자기관리, 식이요법, 허리 관리, 관절 통증, 무릎 관리, 코로나19

#### 3. 치매 예방 게임하기
<div align="center">
<img src="https://user-images.githubusercontent.com/40076944/131533810-7ad57c8a-2690-4d68-9639-3874b8b253ad.png" alt="drawing" width="700"/><br/>
<img src="https://user-images.githubusercontent.com/40076944/131533873-6761532c-1c72-42c6-a482-c93506d3974a.png" alt="drawing" width="700"/>
</div>
<br>
건강 게임에는 하나빼기, 간단 연산, 카드 뒤집기 총 3가지 게임이 있습니다.<br>
노인분들은 건강 게임으로 두뇌를 활발히 만들고 치매를 예방합니다.<br>
- 특정 게임은 난이도를 조절해서 플레이할 수 있습니다.<br><br>

#### 4. 기타
<div align="center">
<img src="https://user-images.githubusercontent.com/40076944/131539367-237c90e3-434e-4eae-b4e8-2d38dff08280.png" alt="drawing" width="700"/>
</div>
<br>
어플을 처음 실행하면 사용자의 정보를 입력 받습니다. 이후에는 마이페이지에서 정보 수정이 가능합니다.<br/>
마이페이지에서는 자가 진단 결과 통계와 건강 게임 최고 기록을 한 눈에 확인할 수 있습니다. <br/>
자가진단 결과 초기화, 게임 결과 초기화, 계정 초기화가 각각 가능합니다. <br/><br/><br/>

## 2. 제작 기간 & 참여 인원
- 제작 기간: 2020.9.18 ~ 2021.08.31
- 제작자
  1. [이유진](https://github.com/Ujaa) : 마이페이지, 튜토리얼, 건강/질병 정보 구현
  2. [홍진서](https://github.com/Hong-Jinseo) : 자가진단, 게임 구현
<br/><br/><br/>

## 3. 사용한 기술
- Java
- Gradle
- roomDB
- SQLite
 <br/><br/><br/>

## 4. 프로젝트 설계
### 📄 구성도
<div align="center">
<img src="https://user-images.githubusercontent.com/40076944/131539532-b12a665b-3ddd-475d-945c-e9851571f6ab.png" alt="drawing" width="700"/>
</div>
<br><br>

### 📁 프로젝트 구조
```
ChanNuLi  
├─ Tutorial  
├─ Main  
│  ├─ MyPage  
│  │  ├─ EditUserInfo  
│  │  ├─ EditDiseaseStatus  
│  │  ├─ SelfDisgnosisHistory  
│  │  ├─ GameHistory  
│  │  ├─ ApplicationDescription
│  │  └─ ResetApplication  
│  ├─ SelfDiagnosisMain  
│  │  ├─ SelfDiagnosis  
│  │  ├─ CheckResult  
│  │  │  ├─ OrderByDate  
│  │  │  └─ OrderBySymtpom  
│  ├─ HealthInfo  
│  │  ├─ Search  
│  │  └─ Video  
│  ├─ DiseaseInfo  
│  │  ├─ Name  
│  │  ├─ Definition  
│  │  ├─ Cause  
│  │  ├─ Symptom  
│  │  ├─ treatment  
│  │  └─ precaution  
│  ├─ GameMain  
│  │  ├─ Game1  
│  │  ├─ Game2  
└─ └─ └─ Game3 
```
<br/><br/>

### ✏ 데이터베이스 스키마

#### 1. 자가진단 질문 저장 DB (SQLite) 

<table>
    <thead>
        <tr>
            <th colspan=3>테이블 이름</th>
            <th colspan=3>disease_n</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>속성 이름</td>
            <td>데이터 타입</td>
            <td>널 허용 여부</td>
            <td>기본값</td>
            <td>기본키</td>
            <td>외래키</td>
        </tr>
        <tr>
            <td>num</td>
            <td>INTEGER</td>
            <td>NOT NULL</td>
            <td></td>
            <td>PK</td>
            <td></td>
        </tr>
        <tr>
            <td>question</td>
            <td>TEXT</td>
            <td>NOT NULL</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </tbody>
</table>

#### 2. 질병 정보 저장 DB (SQLite) 
<table>
    <thead>
        <tr>
            <th colspan=3>테이블 이름</th>
            <th colspan=3>diseaseinfo</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>속성 이름</td>
            <td>데이터 타입</td>
            <td>널 허용 여부</td>
            <td>기본값</td>
            <td>기본키</td>
            <td>외래키</td>
        </tr>
        <tr>
            <td>id</td>
            <td>INTEGER</td>
            <td>NOT NULL</td>
            <td></td>
            <td>PK</td>
            <td></td>
        </tr>
        <tr>
            <td>name</td>
            <td>TEXT</td>
            <td>NOT NULL</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>definition</td>
            <td>TEXT</td>
            <td>NOT NULL</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>cause</td>
            <td>TEXT</td>
            <td>NULL</td>
            <td>없음.</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>symptom</td>
            <td>TEXT</td>
            <td>NULL</td>
            <td>없음.</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>treatment</td>
            <td>TEXT</td>
            <td>NULL</td>
            <td>없음.</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>precaution</td>
            <td>TEXT</td>
            <td>NULL</td>
            <td>없음.</td>
            <td></td>
            <td></td>
        </tr>
    </tbody>
</table>

#### 3. 자가진단 결과 저장 DB (Room Database)
<table>
    <thead>
        <tr>
            <th colspan=3>테이블 이름</th>
            <th colspan=3>Result_table</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>속성 이름</td>
            <td>데이터 타입</td>
            <td>널 허용 여부</td>
            <td>기본값</td>
            <td>기본키</td>
            <td>외래키</td>
        </tr>
        <tr>
            <td>id</td>
            <td>INT</td>
            <td>NOT NULL</td>
            <td></td>
            <td>PK</td>
            <td></td>
        </tr>
        <tr>
            <td>disease</td>
            <td>INT</td>
            <td>NOT NULL</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>count</td>
            <td>INT</td>
            <td>NOT NULL</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>date</td>
            <td>STRING</td>
            <td>NOT NULL</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </tbody>
</table>
<br/><br/>

### ✏ shared-preference 스키마

#### 1. 사용자 정보 preference
<table>
    <thead>
        <tr>
            <th colspan=2>preference 이름</th>
            <th colspan=2>USER_INFOMATION</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>속성 이름</td>
            <td>데이터 타입</td>
            <td>기본값</td>
            <td>비고</td>
        </tr>
        <tr>
            <td>USER_NAME</td>
            <td>STRING</td>
            <td></td>
            <td>1~5자리 문자 입력</td>
        </tr>
        <tr>
            <td>USER_BIRTH</td>
            <td>INT</td>
            <td></td>
            <td>1901~2021 사이 숫자 입력</td>
        </tr>
        <tr>
            <td>USER_GENDER</td>
            <td>STRING</td>
            <td></td>
            <td>MAN, WOMAN</td>
        </tr>
        <tr>
            <td>USER_DISEASE</td>
            <td>STRING</td>
            <td>NULL</td>
            <td></td>
        </tr>
    </tbody>
</table>

#### 2. 게임 결과 preference
<table>
    <thead>
        <tr>
            <th colspan=2>preference 이름</th>
            <th colspan=2>game_score_pref</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>속성 이름</td>
            <td>데이터 타입</td>
            <td>기본값</td>
            <td>비고</td>
        </tr>
        <tr>
            <td>best_score_game1_lv1</td>
            <td>INT</td>
            <td>0</td>
            <td></td>
        </tr>
        <tr>
            <td>best_score_game1_lv2</td>
            <td>INT</td>
            <td>0</td>
            <td></td>
        </tr>
        <tr>
            <td>best_score_game1_lv3</td>
            <td>INT</td>
            <td>0</td>
            <td></td>
        </tr>
        <tr>
            <td>best_score_game2_lv1</td>
            <td>INT</td>
            <td>0</td>
            <td></td>
        </tr>
        <tr>
            <td>best_score_game2_lv2</td>
            <td>INT</td>
            <td>0</td>
            <td></td>
        </tr>
        <tr>
            <td>best_score_game2_lv3</td>
            <td>INT</td>
            <td>0</td>
            <td></td>
        </tr>
        <tr>
            <td>best_score_game3_lv0</td>
            <td>INT</td>
            <td>0</td>
            <td></td>
        </tr>
    </tbody>
</table>

<br/><br/><br/>



## 5. 기타
### 🔧 SDK version
- Minimum SDK version : 24 <br/>
- Target SDK version : 30

### 📣 주의 사항
- 해당 어플리케이션은 스마트폰만을 대상으로 합니다. 패드 등의 다른 디바이스는 고려되지 않습니다.
- 해당 어플리케이션 구축에 필요한 모든 콘텐츠-이미지, 음성 파일 등-는 프로젝트 용도 외의 사용이나 무단 복제가 불가합니다.
- 해당 어플리케이션의 저작권은 제작자에게 있습니다
- 자가진단은 병원에 가지 않고 건강 상태를 점검하기 위한 기능으로, 보다 정확한 진단은 전문가와 상담 후 알 수 있습니다.
- 건강을 위해 게임은 하루 30분 미만으로 즐기세요.

### 📃 자료 출처
#### 1. 자가 진단
  - [고혈압-서울동부고용노동부청](http://www.moel.go.kr/local/seouldongbu/info/dataroom/view.do;jsessionid=C6SZ5jpq27fLezvfFzHd6wFD6diK2EvQPtdEw7JhdNm9aaeg0yPgcKpvHm21wcx3.moel_was_outside_servlet_wwwlocal?bbs_seq=1186720313180)
  - [골관절염-헬스조선](https://m.health.chosun.com/svc/news_view.html?contid=2018050201725)
  - [고지혈증-김용기내과](https://blog.naver.com/8799982/221438863613)
  - [요통-인슈넷](https://www.insunet.co.kr/)
  - [당뇨-매일경제](https://www.mk.co.kr/news/it/view/2017/04/229635/)
  - [골다공증-헬스인뉴스](https://www.healthinnews.co.kr/news/articleView.html?idxno=15007)
  - [치매-헬스인뉴스](https://www.healthinnews.co.kr/news/articleView.html?idxno=23136)
#### 2. 질병 정보
  - [고혈압-서울아산병원](http://www.amc.seoul.kr/asan/healthinfo/disease/diseaseDetail.do?contentId=31322)
  - [골관절염-서울아산병원](http://www.amc.seoul.kr/asan/healthinfo/disease/diseaseDetail.do?contentId=30828)
  - [류마티스 관절염-서울아산병원](http://www.amc.seoul.kr/asan/healthinfo/disease/diseaseDetail.do?contentId=30822)
  - [고지혈증-삼성서울병원](http://www.samsunghospital.com/home/healthInfo/content/contenView.do?CONT_SRC=CMS&CONT_SRC_ID=09a4727a8000f39a&CONT_CLS_CD=001020001010&CONT_ID=2141)
  - [요통-서울아산병원](http://www.amc.seoul.kr/asan/healthinfo/disease/diseaseDetail.do?contentId=31705)
  - [좌골신경통-서울아산병원](http://www.amc.seoul.kr/asan/healthinfo/disease/diseaseDetail.do?contentId=31919)
  - [당뇨병-서울아산병원](http://www.amc.seoul.kr/asan/healthinfo/disease/diseaseDetail.do?contentId=31596)
  - [골다공증-서울아산병원](http://www.amc.seoul.kr/asan/healthinfo/disease/diseaseDetail.do?contentId=31611)
#### 3. 건강 정보
  - [유튜브-노인 건강관리](https://www.youtube.com/results?search_query=%EB%85%B8%EC%9D%B8+%EA%B1%B4%EA%B0%95%EA%B4%80%EB%A6%AC)

### 🖌 폰트
- [DK Cool Crayon](https://www.dafont.com/dk-cool-crayon.font)
- [개미 똥구멍](https://sangsangfont.com/21/?idx=101)
