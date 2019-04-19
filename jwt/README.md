## Sample
### Signin

```bash
curl -X POST http://localhost:8001/auth/signin -H "Content-Type:application/json" -d "{\"username\":\"user\", \"password\":\"123456\"}"
```

```json
{
  "username": "user",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJpYXQiOjE1NTU2NTk4NjIsImV4cCI6MTU1NTY2MzQ2Mn0.7qgFHnhi7wrGDaWkh4mNIktd6xb9R-DCZpv20B300yU"
}
```

### User Info

```bash
curl -X GET http://localhost:8001/me -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJpYXQiOjE1NTU2NTk4NjIsImV4cCI6MTU1NTY2MzQ2Mn0.7qgFHnhi7wrGDaWkh4mNIktd6xb9R-DCZpv20B300yU"
```

```json
{
  "roles": [
    "ROLE_USER",
    "ROLE_ADMIN"
  ],
  "username": "user"
}
```

### User List

```bash
curl -X GET http://localhost:8001/users -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJpYXQiOjE1NTU2NTk4NjIsImV4cCI6MTU1NTY2MzQ2Mn0.7qgFHnhi7wrGDaWkh4mNIktd6xb9R-DCZpv20B300yU"
```

```json
[
  {
    "id": 1,
    "username": "user",
    "password": "123456",
    "roles": [
      "ROLE_USER",
      "ROLE_ADMIN"
    ],
    "enabled": true,
    "authorities": [
      {
        "authority": "ROLE_USER"
      },
      {
        "authority": "ROLE_ADMIN"
      }
    ],
    "accountNonLocked": true,
    "accountNonExpired": true,
    "credentialsNonExpired": true
  }
]
```

### User Info

```bash
curl -X GET http://localhost:8001/user/user -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJpYXQiOjE1NTU2NTk4NjIsImV4cCI6MTU1NTY2MzQ2Mn0.7qgFHnhi7wrGDaWkh4mNIktd6xb9R-DCZpv20B300yU"
```

```json
{
  "id": 1,
  "username": "user",
  "password": "123456",
  "roles": [
    "ROLE_USER",
    "ROLE_ADMIN"
  ],
  "enabled": true,
  "authorities": [
    {
      "authority": "ROLE_USER"
    },
    {
      "authority": "ROLE_ADMIN"
    }
  ],
  "accountNonLocked": true,
  "accountNonExpired": true,
  "credentialsNonExpired": true
}
```

## Reference
[Protect REST APIs with Spring Security and JWT](https://medium.com/@hantsy/protect-rest-apis-with-spring-security-and-jwt-5fbc90305cc5)
