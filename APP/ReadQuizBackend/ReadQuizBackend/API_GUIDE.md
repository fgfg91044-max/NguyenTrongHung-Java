# ReadQuiz Backend API

Base URL khi test tren may tinh:

```text
http://localhost:8080
```

Base URL khi Android Emulator goi backend tren may tinh:

```text
http://10.0.2.2:8080
```

## Dang ky

```http
POST /users/register
Content-Type: application/json
```

```json
{
  "fullname": "Nguyen Van A",
  "email": "a@gmail.com",
  "password": "123456"
}
```

## Dang nhap

```http
POST /users/login
Content-Type: application/json
```

```json
{
  "email": "a@gmail.com",
  "password": "123456"
}
```

## Lay danh sach bai doc

```http
GET /readings
```

## Lay chi tiet bai doc

```http
GET /readings/{id}
```

## Tao bai doc

```http
POST /readings
Content-Type: application/json
```

```json
{
  "title": "Loi ich cua viec doc sach",
  "content": "Noi dung bai doc...",
  "summary": "Tom tat bai doc..."
}
```

Neu `summary` de rong, backend se tu tao tom tat ngan tu `content`.

## Sua bai doc

```http
PUT /readings/{id}
Content-Type: application/json
```

```json
{
  "title": "Tieu de moi",
  "content": "Noi dung moi...",
  "summary": "Tom tat moi..."
}
```

## Xoa bai doc

```http
DELETE /readings/{id}
```

## Mau response

```json
{
  "success": true,
  "message": "Tao bai doc thanh cong",
  "data": {
    "id": 1,
    "title": "Loi ich cua viec doc sach",
    "content": "Noi dung bai doc...",
    "summary": "Tom tat bai doc...",
    "createdAt": "2026-06-29T11:38:10",
    "updatedAt": "2026-06-29T11:38:10"
  }
}
```
