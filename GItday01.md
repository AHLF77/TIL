# 0407 Git 기초 

# GIT 설명

## 1. 왜?

### 1.1 ****git 이용한 버전 관리****

- 분산 버전 관리 시스템
- 버전관리 하는 이유: 백업, 복구, 협업
- (변경사항 + 변경된 버전) SnapShot 관리
- (변경사항+최종버전) Delta 관리

1. 중앙 집중식 버전 관리(SVN) - 서버 컴퓨터(클라우드 등)에서 버전 관리, 개발자들이 수정해서 업로드 - 서로 갖고있는 버전이 다르기 때문에 서버 컴퓨터 손상 시 복구 불가능
2. 분산 버전 관리(git) - 서버 컴퓨터에 있는 버전과 개발자들이 갖고 있는 버전이 동일 - 서버 컴퓨터 손상되어도 복구 가능

### 1.2 github를 이용한 포트폴리오 [포트폴리오 예시]

[jojoldu - Overview](https://github.com/jojoldu)

### 1.3 버전 만들기

- 프로그램 개발에서 수정 내용이 쌓이면 새로 번호를 붙여서 상태와 구분한다. 이 때, 번호를 통해서 구별된 것을 “버전”이라고 부른다.

1.3.1 깃에게 버전이란?

- 문서를 수정하고 저장할 때마다, 생기는 것이라고 생각하면 쉽다.
- 보고서를 작성하면서 만들어지는, <초안> , <수정1> , <수정2> ...  등의 것들이 모두 버전이라고 생각하면 편하다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ca4449e6-8520-44aa-8bad-944ef9b68641/Untitled.png)

- 하지만, 이러한 과정을 1,000번 반복한다면, 어떤 내용을 언제 수정했는지 알 수가 없다.
- 이러한 불편함을 해소한 것이, 바로 깃과 같은 버전 관리 시스템이다. (쉽게 버전을 만들고, 시간과 수정내용까지 기록할 수 있도록 만들어줌)
- 깃에서 버전을 관리하면, **파일이름은 동일하게 유지**되면서, **파일에서 무엇을 변경**했는지 **변경시점마다** 확인이 가능하다. **작업내용도 확인이 가능**하고, **그 버전으로 돌아가는 것** 또한 가능하다.

## 2. 사전 지식

### 2.1 Git Bash 쓰기

![화면 캡처 2022-10-25 104506.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/53c967e9-7844-4311-9980-89b46cf79a06/%ED%99%94%EB%A9%B4_%EC%BA%A1%EC%B2%98_2022-10-25_104506.png)

※ Git 사용시 CLI 환경을 사용해야 한다.

### 명령어 정리

| touch a.txt | a.txt 만들기 |
| --- | --- |
| rm. atxt | a.txt 완전 삭제 |
| mkdir CLI | CLI 폴더 만들기 |
| ls | 구성파일, 폴더 표시 |
| ctrl + L | 진행했던 명령문은 모두 위로 올리고 처음부터 빈 화면으로 시작 |
| cd CLI | CLI 폴더로 들어가 |
| ls -a | 숨김폴더, 상위항목 전부 표시 |
| cd .. | 상위 폴더로 이동 |
| cd . | 현재 폴더로 이동 |
| rm -r CLI | 폴더 지우기 |
| pwd | 현재 위치의 경로를 나타냄 |
| ls -l | 파일이나 디렉터리의 상세정보를 함께 표시 |
| ls -r | 파일의 정렬 순서를 거꾸로 표시 |
| ls -t | 파일 작성 시간 순으로(내림차순) 표시 |
| vim (파일명.파일) | 빔에서 텍스트 문서 만들기 |
| cat | 터미널 창에서 간단히 텍스트 문서 내용을 확인할 때 |

### 2.2 VScode 사용하기

![화면 캡처 2022-10-25 105018.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f7437369-f3d2-456b-b085-30a485b463c1/%ED%99%94%EB%A9%B4_%EC%BA%A1%EC%B2%98_2022-10-25_105018.png)

원하는 디렉토리에서 우클릭 후 ‘Code로 열기’ 클릭

![화면 캡처 2022-10-25 105153.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a65e30fb-5acc-4537-b29c-3de90c5b23d3/%ED%99%94%EB%A9%B4_%EC%BA%A1%EC%B2%98_2022-10-25_105153.png)

ctrl + `을 눌러서 git 터미널 열기 가능

### 2.3 MarkDown

2.3.1 마크 다운이란?

- 마크업과 반대인 개념이 아니라, 마크다운은 마크업의 부분집합
- 경량 마크업이라고도 한다.
- 글에 역할을 부여하는 작업이다.(제목, 본문, 인용 등)
- GitHub에 마크다운을 쓰기 위해서 배운다.

2.3.2 마크다운 장, 단점

- 장점
    - 쉽고 직관적인 문법
    - 쉬운관리
    - 다양하게 지원 가능한 플랫폼과 프로그램
- 단점
    - 표준이 없어서 사용자마다 다른 문법
    - 모든 HTML 마크업 기능 대체 불가능
- 주의사항
    - 마크다운의 목적은 글에 역할을 부여하는 것
    - 글씨 크기를 키우고 싶다는 이유로 내용에 제목 역할 부여하면 안됨.

2.3.3 마크 다운 문법 종류

| 문법 | 개요 | 설명 |
| --- | --- | --- |
| #[space]text | 제목 1 | #개수가 늘어날 수록 글자가 작아진다. 6개까지 가능 |
| [space] | 목록 만들기 |  |
| [tab] | 들여쓰기 | 목록이나 문단을 들여쓴다. |
| [shift]+[tab] | 목록 들여쓰기 취소 |  |
| [Enter][Enter] | 목록 탈출하기 |  |
| 1.[space] | 번호 목록 만들기 |  |
| ‘*text*’ | text | 기울임 |
| ‘**text**’ | text | 굵게 |
| ‘~text~’ | text | 취소선 |
| ‘`println(”Hello World”)`’ | println("Hello World") | 인라인 코드, 소스코드 한 줄 표현 가능 |
| ‘```for a in range(5): print(i)```’ | for a in range(5): print(i) | 블록 코드, 소스코드 여러 줄 표현 가능 |
| ctrl  + / | 마크다운 소스코드 보기 |  |
| |--------| | 표 만들기 |  |
| \ | 뒤에 나오는 문자 그대로 적힌다. | 문법을 무시하고 그대로 표현된다. |
| ![text](image url) | 이미지 삽입 | text는 대체 텍스트 |
| > | 인용 표현 |  |
| [text](link url) | 링크삽입 | 링크가 text로 표시된다. |
| —-, ***, ___ | 수평선 |  |

## GIt 기초

### 3.1 작동 원리

1. git 은 크게 3가지로 구성된다.
    1. Working Directory
    2. Staging Area
    3. git Repository

Working Directory는 사용자가 작업하는 공간이다. 만약 여기서 a.txt를 만들어 편집하고 저장한다고 하자. 이 저장한 파일을 Staging Area로 add 해서 버전관리 준비를 하고 마지막으로 commit을 해 Repository로 보냄으로써 버전관리가 되는 것이다.

### 3.2 실습

3.2.1 Git 초기 설정

| 최초 한 번만 설정

1. 누가 Commit을 했는지 확인할 수 있도록 사용자 이름과 이메일을 설정
2. 수정하고 싶을 때 이름, 이메일 주소만 바꿔서 다시 입력할 수 있다.

```markdown
$ git config --global user.name "이름"
$ git config --global user.email "메일 주소"
```

1. 올바르게 설정 되어있는지 확인

```markdown
$ git config --global -l
$ git config --global --list
```

3.2.2 Git 기본 명령어

1. git init

```markdown
$ git init
```

- 현재 작업 중인 Directory를 git으로 관리한다는 뜻이다.
- git을 초기화 한다는 의미
- `.git`이라는 숨김 폴더를 생성하고, 터미널에는 아래와 같이 표기된다.

```markdown
Initialized empty Git repository in C:/Users/kyle/git-practice/.git/

kyle@KYLE MINGW64 ~/git-practice (master)
```

1. git status

```markdown
$ git status
```

- 깃에서는 버전을 만드는 각 단계마다, 파일 상태를 다르게 표시한다.
- 파일의 상태를 이해하면 이 파일이 버전 관리의 여러 단계 중 어디에 있는지, 그 상태에서 어떤 일을 할 수 있는지 알 수 있다.
- 하지만, 파일의 상태가 눈에 보이는 것이 아니기 때문에, 머릿속으로 떠올려야한다.
- Working Directory와 Staging Area에 있는 파일들의 상태를 알려주는 명령어
- 수시로 status를 확인하여 파일들의 상태를 조회하는 것이 중요하다.
- `Untracked`: Git이 관리하지 않는 파일
- `Tracked`: Git이 관리하는 파일
    - `Unmodified`: 최신상태
    - `Modified`: 수정되었지만 Staging Area에 반영되지 않은 상태
    - `Staged`: Staging Area에 올라간 상태
    
    ![화면 캡처 2022-10-25 113355.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2239b1f8-8e4c-495d-9e98-6545d8f21867/%ED%99%94%EB%A9%B4_%EC%BA%A1%EC%B2%98_2022-10-25_113355.png)
    

3.2.3 git add

```markdown
# 특정 파일
$ git add a.txt

# 특정 폴더
$ git add my_folder/

# 현재 디렉토리에 속한 파일/폴더 전부
$ git add .
```

- 수정된 Working Directory에 있는 파일을 Staging Area로 올리는 명령어
- GIt이 해당 파일을 추적(관리)할 수 있도록 만듦
- 파일 상태를 `Staged`로 변경

3.2.4 git commit

```markdown
$ git commit -m "text"
```

- Staging Area에 있는 파일의 변경사항을 하나의 버전(커밋)으로 저장하는 명령어
- `"text"`는 버전 관리를 위해 해당 버전에 이름을 붙히는 것으로 버전의 특성에 맞게 텍스트를 적는 것을 권장
- 각각의 커밋은 SHA-1 알고리즘에 의해 고유 ID를 갖는다.

3.2.5 git log

```markdown
$ git log
$ git log --oneline
$ git log --graph
$ git log --all
$ git log --reverse
$ git log -p
$ git log -2
```

- 커밋 내용 확인하기: 버전을 관리하기 위해서는, 지금까지 어떤 버전을 만들었는지 알 수 있어야 한다. 또, 각 버전마다의 차이가 있는지 파악하는 것 또한 중요하다.
- 커밋의 내역(ID, 작성자, 시간, 메시지) 조회
    - `oneline`: 한 줄로 요약
    - `--graph`: branch와 merge를 그래프로 표현
    - `--reverse`: 커밋 내역의 순서를 오래된 순으로 조회
    - `-p`: 파일 변경 내용과 같이 조회
    - `-2`: 원하는 갯수 만큼 내역을 조회(임의 숫가 가능)

3.2.6 git diff

- 변경사항 확인하기
- 큰 규모의 프로그램을 짜게 된다면, 수만 줄짜리 소스코드를 수정한 다음 저장소에 있는 최근 버전과 비교해서 어느 부분이 다른지 찾아야 한다면, 어떨까?
    
    ⇒ 이럴 때, **git diff** 명령을 사용하면, **작업 트리에 있는 파일과 스테이지에 있는 파일을 비교**하거나, **스테이지에 있는 파일과 저장소에 있는 최신 커밋을 비교**해서 **수정한 파일을 커밋하기 전애 최종적으로 검토**할 수 있다.
    
1. 2개의 버전이 저장되어 있다고 가정을 하자.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4cb0eacc-af7b-4f06-b9b5-9dbf0560a531/Untitled.png)

1. vim으로 hello.txt. 파일을 열고 기존의 내용 중에서 숫자 2를 지우고 two로 바꾸기

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6b5e8f23-bf05-4521-8f0e-c442a53fcbad/Untitled.png)

1. git status로 확인을 하면, hello.txt 파일은 수정이 되었지만, 스테이징 상태가 아니라고 나온다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e529ca6-478c-4a03-a1e0-0c4994d60cf4/Untitled.png)

- modified: hello.txt (hello.txt 가 수정되었다.)
- changes not staged for commit (커밋을 위해 스테이지로 변화시키지는 못했다.)

1. 방금 수정한 hello.txt 와 최근에 커밋한 hello.txt 가 무엇이 다른지 확인하기 위해서 **git diff** 를 사용해본다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/313d0a06-7854-4685-9bc9-97fe192c8576/Untitled.png)

여러가지 내용이 나오지만, 2개만 확인하면 된다.

- ‘-’ 추가로 넣은, 문자 test_2 (제일 최근에 commit 한 내용)
- ‘+’ 추가로 넣은, 문자 test_two (방금 수정한 내용)

이 두 개를 통해서, "2"를 "two" 로 바꿨다는 것을 파악할 수 있다.

1. 만약, 수정한 내용으로 다시 버전을 만들려면 스테이지에 올리고 커밋하면 되고, **수정한 내용을 버린다면 git checkout 명령을 이용**해서 수정취소를 하면 된다.

## 4. 작업 되돌리기

- 스테이지에 올렸던 파일을 내리거나 커밋을 취소하는 등 각 단계로 돌아가는 방법을 배우기

### 4.1 작업 트리에서 수정한 파일 되돌리기 - git checkout

파일을 수정한 뒤 소스가 정상적으로 돌아가지 않을 때, 보통 수정한 내용을 취소하고 가장 최신의 버전 상태로 돌아가야 한다.

이런 상황일 때마다 일일이 수정한 소스를 찾는 것은 굉장히 번거로운 과정이다. 

또한, 수 천줄이 넘는 코드를 수정해야 하는 상황이라면, 거의 불가능하다.

이럴 때, checkout 명령어를 사용하면 “작업 트리”에서 수정한 내용을 취소할 수 있다.

1. Vim으로 파일을 열고 3을 three로 바꾼 뒤, 저장한다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ccf51713-ac8f-4b02-b944-22f6406c7e1e/Untitled.png)

1. git status를 통해서 파일의 상태를 확인해보면, 스테이지에 올라가 있지 않고 작업트리에서만 변경이 일어나 있는 상황이다.  status를 통해서 확인할 수 있는 문장에서, checkout을 통해서 변경사항을 취소하라는 구문이 있다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/808d33cb-1f13-4167-aae2-40c9575f1a9b/Untitled.png)

- use "git restroe <file> ... " to discard changes in working directory = > 작업 디렉터리에서 변화를 버리기 위해서는 **'git restore'** 를 사용하라.


## 4. 주의할점

#### Github

1. git init하지 않기
2. 상위폴더에 init하지 않기
3. 깃허브에서 수정 금지

