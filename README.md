# 🏦 Bank API Automation – Karate Mock Framework

Proyecto de automatización de APIs usando **Karate DSL** con un **Mock Server bancario** que simula operaciones reales como autenticación, transferencias y consulta de transacciones.

El objetivo es practicar **automatización de pruebas de APIs** y validar flujos E2E sin depender de un backend real.

---

# 📌 Objetivo del proyecto

Simular una API bancaria para probar diferentes escenarios de negocio usando Karate.

El mock server permite validar reglas como:

* autenticación de usuario
* transferencias entre cuentas
* validación de fondos
* manejo de errores
* generación de identificadores únicos

---

# ⚙️ Tecnologías utilizadas

* Java 17
* Karate DSL
* JUnit 5
* Gradle
* Karate Mock Server

---

# 📁 Estructura del proyecto

```
bank-api-karate
│
├─ mock
│   └─ bank-mock.feature
│
├─ features
│   ├─ auth
│   │   └─ getToken.feature
│   │
│   ├─ transfers
│   │   └─ transfer-success.feature
│
├─ config
│   └─ credentials.txt
│
├─ data
│   └─ auth
│       └─ loginRequest.json
│
├─ runners
│   ├─ TestRunner.java
│   └─ MockServerRunner.java
│
├─ karate-config.js
│
└─ README.md
```

---

# 🧪 Mock API simulada

El mock implementa los siguientes endpoints:

| Método | Endpoint        | Descripción                 |
| ------ | --------------- | --------------------------- |
| POST   | `/auth/login`   | genera token                |
| POST   | `/transfers`    | realiza transferencias      |
| GET    | `/transactions` | historial de transferencias |

Servidor local:

```
http://localhost:8080
```

---

# 🔐 Autenticación

El endpoint `/auth/login` genera un token dinámico usando UUID.

```
java.util.UUID.randomUUID()
```

Ejemplo de respuesta:

```json
{
  "token": "6b2d0b19-0b8f-4b9f-9a2e-3f7e1b6e1b29"
}
```

El token se utiliza en los requests:

```
Authorization: Bearer <token>
```

---

# 💸 Transferencias

El endpoint `/transfers` simula reglas de negocio de un sistema bancario.

Validaciones implementadas:

| Caso                 | Respuesta          |
| -------------------- | ------------------ |
| Cuenta inexistente   | ACCOUNT_NOT_FOUND  |
| Fondos insuficientes | INSUFFICIENT_FUNDS |
| Transferencia válida | SUCCESS            |

Ejemplo de request:

```json
{
  "fromAccount": "1001",
  "toAccount": "2001",
  "amount": 200
}
```

Ejemplo de respuesta exitosa:

```json
{
  "status": "SUCCESS",
  "transactionId": "c4a6a42e-34fd-4f3d-bb90-27a2f5b4c3c2",
  "amount": 200
}
```

---

# 📜 Historial de transacciones

Endpoint:

```
GET /transactions
```

Ejemplo de respuesta:

```json
[
  {
    "transactionId": "6c7f3c92-89d1-4e39-9c2b-4b4f9b3f5c2a",
    "from": "1001",
    "to": "2001",
    "amount": 200
  }
]
```

---

# ▶️ Ejecutar Mock Server

Ejecutar:

```
MockServerRunner
```

El servidor quedará activo en:

```
http://localhost:8080
```

---

# ▶️ Ejecutar pruebas automatizadas

Ejecutar los tests:

```
gradlew test
```

o

```
./gradlew test
```

---

# 🧪 Escenario automatizado

Flujo probado:

```
Login
   ↓
Generar token
   ↓
Realizar transferencia
   ↓
Validar respuesta
   ↓
Validar transactionId
```

Ejemplo de validación en Karate:

```gherkin
Then status 201
And match response.status == "SUCCESS"
And match response.transactionId != null
```

---

# 📊 Logs de requests y responses

Configurados en los tests:

```gherkin
* configure logPrettyRequest = true
* configure logPrettyResponse = true
```

Esto permite visualizar:

* request
* response
* headers
* body
* response time

---

# 📁 Manejo de datos externos

Credenciales almacenadas en:

```
config/credentials.txt
```

Ejemplo:

```
username=admin
password=password
```

Lectura desde Karate:

```gherkin
* def credentials = read('classpath:config/credentials.txt')
```

---

# 📈 Reportes de ejecución

Karate genera reportes automáticamente en:

```
build/karate-reports/
```

Abrir:

```
karate-summary.html
```

---

# 🧠 Buenas prácticas aplicadas

* separación de datos de prueba
* autenticación reutilizable
* mock server para aislar pruebas
* generación de identificadores únicos
* logs detallados de requests y responses
* estructura modular de features

---

# 🚀 Posibles mejoras

Este proyecto puede ampliarse agregando:

* endpoint `/accounts/{id}`
* validación real de `Authorization`
* límite de transferencias diarias
* generación de datos aleatorios
* ejecución paralela de tests
* integración con CI/CD
* pruebas de carga

---

# 👨‍💻 Autor

Proyecto desarrollado como práctica de **QA Automation con Karate DSL**, simulando una **API bancaria mediante Mock Server** para validar flujos E2E.