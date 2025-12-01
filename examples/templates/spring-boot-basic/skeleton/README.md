# $Nombre Proyecto (Actualizar)

<div align="center">

# $Descripción Proyecto (Actualizar

### Spring Boot - Plantilla Proyecto Limpia

<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" width="50">
<img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/java_logo_icon_168609.png" width="120" alt="Java Logo">

**Generado automáticamente con Backstage Scaffolder**

</div>

---

## 🧱 Descripción

Este proyecto ha sido generado con el *template* **Spring Boot Clean Architecture**.
Su objetivo es ofrecer una estructura estándar, escalable y alineada con buenas prácticas utilizadas en entornos profesionales (hexagonal/clean architecture).

Incluye una arquitectura clara basada en capas lógicas:

- **Controller** (endpoints REST)
- **Service** (lógica de negocio)
- **Repository** (acceso a datos)
- **Domain / Model** (entidades)
- **Config** (opcional)
- **Logger** (opcional)
- **Utils** (opcional)
- **Mapper** (opcional)
- **Constants** (opcional)

---

## 🏗️ Estructura del proyecto generada

```plaintext
${{ values.appName }}/
 ├── src/
 │   ├── main/
 │   │   ├── java/com/example/${{ values.appName }}/
 │   │   │   ├── controller/
 │   │   │   ├── service/
 │   │   │   ├── repository/
 │   │   │   ├── model/
 │   │   │   ├── config/        (opcional)
 │   │   │   ├── logger/        (opcional)
 │   │   │   ├── util/          (opcional)
 │   │   │   ├── mapper/        (opcional)
 │   │   │   ├── constant/      (opcional)
 │   │   └── resources/
 │   │       ├── application.yml
 │   └── test/
 ├── pom.xml
 ├── README.md
 └── catalog-info.yaml
```
