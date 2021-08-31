
# 찬누리

## 목차
[1. 프로젝트 설명](#1-프로젝트-설명)<br>
[2. 제작 기간 & 참여 인원](https://github.com/Godeok/ChanNuLi/blob/master/README.md#2-%EC%A0%9C%EC%9E%91-%EA%B8%B0%EA%B0%84-%EB%B0%8F-%EC%B0%B8%EC%97%AC-%EC%9D%B8%EC%9B%90)<br>
[3. 사용한 기술](#3-사용한-기술)<br>
[4. 프로젝트 설계](#4-프로젝트-설계)<br>
[5. 주의 사항](#5-주의-사항)<br>
[6. 기타](#6-기타)

## 1. 프로젝트 설명
### 어플 이름
<div align="center">
  <h3>찬누리</h3>
<img src="https://user-images.githubusercontent.com/40076944/131518766-374ec45f-b2a7-4b9a-9618-6b07f12afec5.png" width="200" height="200">
</div>

### 어플 소개 및 목적
찬누리는 **노인 건강 관리 어플**입니다. 노인분들은 찬누리를 사용하면서 스스로 건강 상태를 진단하고, 다양한 질병과 건강 정보를 얻고, 간단한 게임을 통해 치매도 예방할 수 있습니다. 또한, 찬누리는 노인분들이 어플을 쉽게 사용하실 수 있도록 간편한 화면 구성과 조작 방법 제공합니다.

### 어플 기능 소개
#### 1. 자가 진단하기
![image](https://user-images.githubusercontent.com/40076944/131531526-0cdd8940-5cd0-4e22-952d-5747b310e4fa.png)

총 7 종류의 대표적인 노인 질병을 노인분들 스스로 진단하고 예방합니다.<br>
자가 진단 후 결과를 저장하고 결과 확인 페이지에서 진단을 한 날짜 순이나 질병 종류 별로 결과를 확인할 수 있습니다.<br>
- *자가 진단 종류: 고혈압, 골관절염, 고지혈증, 요통, 당뇨병, 골다공증, 치매*<br><br>

#### 2. 질병/건강 정보 보기
![image](https://user-images.githubusercontent.com/40076944/131533748-c6226c77-ae74-46a8-a641-83e90b328019.png)

대표적인 노인 질병 정보와 건강 관리에 필요한 정보를 검색하고 확인할 수 있습니다.<br>
노인분들이 검색 엔진을 사용할 때 검색 키워드 선택의 어려움을 줄이고자 건강 관리 추천 검색 키워드를 제공합니다. 추가로, 노인분들께 추천하는 건강 관리 영상 또한 볼 수 있습니다.
- *제공하는 질병 정보 종류: 고혈압, 골관절염, 류마티스 관절염, 고지혈증, 요통, 좌골신경통, 당뇨병, 골다공증*<br><br>

#### 3. 건강 게임하기
![image](https://user-images.githubusercontent.com/40076944/131533810-7ad57c8a-2690-4d68-9639-3874b8b253ad.png)
![image](https://user-images.githubusercontent.com/40076944/131533873-6761532c-1c72-42c6-a482-c93506d3974a.png)


건강 게임에는 하나빼기, 간단 연산, 카드 뒤집기 총 3가지 게임이 있습니다.<br>
노인분들은 건강 게임으로 두뇌를 활발히 만들고 치매를 예방합니다.<br>
- *몇 가지 게임은 난이도를 조절해서 플레이할 수 있습니다.*<br><br>

#### 4. 기타
![image](https://user-images.githubusercontent.com/40076944/131533926-1dc70a18-50c4-4b09-b631-9a24b36c2b46.png)

어플을 처음 실행하면 사용자의 정보를 입력 받습니다. 이후에는 마이페이지에서 정보 수정이 가능합니다.<br/>
마이페이지에서는 자가 진단 결과 통계와 건강 게임 최고 기록을 한 눈에 확인할 수 있습니다.

## 2. 제작 기간 & 참여 인원
- 제작 기간: 2020.9.18 ~ 2021.08.31
- 제작자: 이유진, 홍진서

## 3. 사용한 기술
- Java
- Gradle
- roomDB
- SQLite

## 4. 프로젝트 설계
### 프로젝트 구조
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

### 데이터베이스 스키마

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

### shared-preference 스키마

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




## 5. 주의 사항

## 6. 기타
### - 자료 출처
#### 1. 자가 진단
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
