## 0408 Git 사용 특강

# GitHub 설명

## 1. GItHub

### 1.1 원격 저장****(Remote Repository)****

- GIt의 주요 목적 중 하나인 협업을 위해 로컬 저장소와 원격 저장소의 연동

### 1.2 GitHub에서 원격 저장소 생성

![167742810-ccf732e3-24af-43cd-bb27-a4085a1de9e1.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0e90647d-7457-4aa6-87f3-58aebbf1dae8/167742810-ccf732e3-24af-43cd-bb27-a4085a1de9e1.png)

- 화면 오른쪽 상단(+) 버튼을 누르고 New Repository 클릭

![168068958-5bccf8ad-d1b3-4b2e-91bf-463628612af7.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f6011f54-946c-40b0-90e4-a2f7aa46f61b/168068958-5bccf8ad-d1b3-4b2e-91bf-463628612af7.png)

- 저장소의 이름, 설명, 공개 여부를 선택하고 Create repository를 클릭

### 1.3 로컬 저장소와 원격 저장소 연결

1. 원격 저장소가 잘 생성되었는지 확인 후, 저장소의 주소를 복사

![168069557-01434f13-9869-4214-8684-4e97670cebf4.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/84aaecd8-3341-4c6f-adac-36988d26bb38/168069557-01434f13-9869-4214-8684-4e97670cebf4.png)

1. 홈 디렉토리의 폴더에서 VScode 열기
2. `git init`을 통해 폴더를 로컬 저장소로 만들기

```markdown
$ git init
```

1. `git remote`
- 등록: `git remote add <이름> <주소>`

```markdown
$ git remote add origin https://github.com/earlyou/TIL.git
```

- 조회

```markdown
$ git remote -v
```

- 삭제
    - 연결을 끊는 것이지, 저장소를 삭제 하는 것이 아니다.
    
    ```markdown
    $ git remote rm origin
    $ git remote remove origin
    ```
    

### 1.4 ****원격 저장소에 업로드****

- 커밋을 원격저장소에 업로드 하는 것
- 로컬 저장소에서 커밋을 생성해야 업로드 할 수 있다.
1. 로컬 저장소에서 커밋 생성

```markdown
$ git add a.txt

$ git commit -m "first commit"

# 커밋 확인
$ git log --oneline
```

1. `git push`
- 로컬 저장소의 커밋을 원격 저장소에 업로드하는 명령어
- `git push <저장소 이름> <브랜치 이름>`
- `-u`를 사용하면 두 번째 푸쉬 부터는 저장소 이름과 브랜치 이름 생략 가능

```markdown
$ git push origin master

$ git push -u origin master
$ git push
```

1. vscode 자격 증명
- 처음으로 원격 저장소와 연동을 할 때는 `push`하려는 사용자가 로그인을 함으로써 자격을 증명한다.

![168071589-e8012338-dd73-4a99-b127-5acc6c57bc34.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/adeb0cca-a101-437b-b32f-c8340a5adaf1/168071589-e8012338-dd73-4a99-b127-5acc6c57bc34.png)

- Sign in with your browser

![168071709-7b457826-e9e6-4d71-9d76-da0226746905.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/61bdafc8-2591-4aad-b2d1-6ed9495b8878/168071709-7b457826-e9e6-4d71-9d76-da0226746905.png)

- Authorize GitCredentialManager

![168071800-ee78b247-5836-4350-ac9f-ebeae0f5b606.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dc8ccc26-4ec3-419b-9ea3-f89d067bae7e/168071800-ee78b247-5836-4350-ac9f-ebeae0f5b606.png)

※ 증명 완료 후 git push 완료

1. 원격 저장소에서 정상 업로드 확인
