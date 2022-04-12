# spring-template
Plantilla para iniciar proyectos de spring

# Version
![version](https://img.shields.io/badge/version-1.0.0-blue.svg)

Para más detalle mira el archivo [CHANGELOG](CHANGELOG)

### Pre-requisitos 📋
Tener instalado
* Eclipse o tu IDE favorito
* Maven
* Java 1.8
* Lombok
* MySQL

### Instalación 🔧

Proyecto generado y compilado con maven

```
mavn clean install
```

## Ejecutando las pruebas ⚙

Para ejecutar las pruebas y comprobar la calidad del código en sonar

```
mvn clean install site sonar:sonar -Psonar
```

### Y las pruebas unitarias de codificación ⌨️

Las pruebas se realizaron con mockito y junit

```
  @Test
  public void metodoTest() {
    Mockito.when(mock).thenReturn(resultadoEsperado);
    Assert.assertNotNull(metodoa probar);
  }
```

## Despliegue 📦

## Construido con 🛠️

* Spring boot 2.5.3
* Spring Cloud
* Java 1.8
* Maven
* Intellij IDEA

### Generación de Reportes 📋
Brindan un panorama general de varios reportes que son generados automaticamente por Maven.


Los reportes se generan en la carpeta target > site > index.html

## Versionado 📌

Usamos [GithHub](https://github.com/felipemonzon/spring-template) para el versionado.

## Autores ✒️

* **Felipe Monzón** - *WEB AND JAVA DEVELOPER*

## Contribuyendo 🖇


## Licencia 📄

Este proyecto está bajo la Licencia GNU General Public License v3.0 - mira el archivo [LICENSE.md](LICENSE) para detalles