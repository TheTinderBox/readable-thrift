# Readable Thrift

Конвертер JSON объектов в бинарный протокол Thrift.

## Установка

```bash
$ ./gradlew
```

## Как пользоваться

Библиотека на базе Gradle Maven пакета, который может быть добавлен в проект как зависимость. Представляет четыре
простых метола для конвертирования в чистый бинарный вид или в Base64-encoded вид.

***Внимание:*** Поддерживается только стандартный бинарный протокол..
`Compact binary` и `JSON` (отличвается от того, что используется в текущем конвертере)
не поддерживаются.

***Внимание:*** При работе в интерактивном режиме, читает stdin.

### Расшифровка сообщений - Thrift binary в JSON

Выдаст ответ в stdout в виде JSON

```bash
cat encoded-base64-string.txt | java -jar build/libs/readablethrift-1.0.0-all.jar decode
```

### Кодирование сообщений - JSON в Thrift binary

Выдаст ответ в stdout в виде base64 хэша

```bash
cat object.json | java -jar build/libs/readablethrift-1.0.0-all.jar encode
```

### Структура входного JSON объекта

```json
{"message": {
    "name": "login",
    "type": "call",
    "fields": [{
        "id": 0,
        "type": "struct",
        "value": [
            {
                "id": 0,
                "type": "string",
                "value": "user"
            },
            {
                "id": 1,
                "type": "string",
                "value": "password!"
            }
        ]
    },
    {"id": 1, "type": "bool", "value": true},
    {"id": 2, "type": "list", "value":
        {
            "elemType": "i32",
            "list": [1, 2, 3]
        }
    },
    {"id": 3, "type": "map", "value":
        {
            "keyType": "string",
            "valueType": "string",
            "map": {
                "dog": "bark",
                "cat": "meow"
            }
        }
    }],
    "seqid": 0
}}
```