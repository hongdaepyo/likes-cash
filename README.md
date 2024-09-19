# LikesCash
## 좋아요 수로 보상을 받는 서비스를 구현하는 토이 프로젝트

### 개요
- 회원이 판매자의 상품을 보고 그 주제에 맞는 게시글을 작성하고 링크 또는 캡쳐 이미지를 올린다.
- 다른 회원의 게시물에 좋아요를 누를 수 있다.
- 게시물을 올린 회원은 좋아요 수에 따라서 금전적인 보상을 받을 수 있다.
- 판매자는 좋아요수가 많은 상위 10개 게시물에 추가 보상을 지급하고 홍보목적으로 자유롭게 활용할 수 있다.

### 요구사항
#### 회원
- [x] 서비스에 가입할 수 있다.
- [x] 판매자가 올린 상품을 조회할 수 있다.
- [x] 상품 상세에서는 상품 정보와 다른 사람이 작성한 게시물을 볼 수 있다.
  - [x] 게시물은 최근에 제출한 순서로 노출된다.
- [ ] SNS나 블로그 같은 곳에 글을 쓰고 링크나 캡쳐 이미지를 제출할 수 있다.
- [ ] 다른 회원의 게시글에 좋아요를 할 수 있다.
  - [ ] 한 게시글에 좋아요는 한번만 할 수 있다.
  - [ ] 좋아요 취소를 할 수 있다.
- [ ] 문제가 있는 게시물을 신고할 수 있다. 
- [ ] 게시글의 좋아요 수가 특정 개수에 도달하면 그에 맞는 보상을 받을 수 있다.
- [ ] 상위 N명은 순위에 따라 추가 보상을 받을 수 있다.
- [ ] 로그아웃 할 수 있다.
- [ ] 탈퇴할 수 있다.

### 판매자
- [x] 어떤 주제로 사용자가 게시글을 올릴지를 알려주는 상품을 만든다.
- [ ] 상품은 최소 7일 이상 노출시킬 수 있다.
  - [ ] 노출된지 3일이 지난 시점부터 매일 예치금이 차감된다.
  - [ ] 상품이 노출되기 시작한지 3일까지는 예치금 차감 대상에서 제외된다.
- [ ] 로그아웃 할 수 있다.

### 관리자
- [ ] 판매자가 올린 상품을 관리자가 승인해주면 회원에게 노출된다.
- [ ] 신고된 게시물을 노출되지 않도록 처리할 수 있다.
