# Android App

Esta solución se compone de dos proyectos:

- **app**: Como su nombre lo indica este proyecto representa la aplicación en android y contiene todas las referencias al código nativo y es la que se encarga de realizar la comunicación con las APIs.

- **MeliSearchCore**: Este proyeto representa el dominio de la aplicación y en el se encuentra la representación de todos los objetos del dominio, repositorios e interfaces que son utilizados por la App para definir las reglas de negocio.

# MeliSearchCore

## datasource_abstractions

En esta carpeta se enuentran las interfaces que definen los dataSources que utilizan los repositorios para definir los distintos procesos de la aplicación, en esta instancia se provee una interfaz tanto para los repositorios locales como remotos.

> Se define como datasource local a todos los repositorios que se encargan de manipular información directamente desde el dispositivo y en cambio un datasource remoto es el cual se debe comunicar con las APIs y no trabaja con los datos almacenados en el dispositivo.

## model

En esta carpeta se definen todos los objetos del dominio utilizados a lo largo de la aplicación.

> Cabe resaltar que estas clases no guardan una relación 1:1 con los datos retornados por las APIs sino que contienen exclusivamente los campos que son necesarios para cumplir con la lógica de la aplicación

## repositories

En esta carpeta se encuentran los repositorios que son los encargados de definir la comunicación con los dataSources y son la única manera que tiene la aplicación de comunicarse ellos, de todos modos, los repositorios abstraen a la aplicación de tener que decidir si deben utilizar un repositorio local o remoto, ese tipo de decisiones las toma directamente el repositorio.

# app

## application

En esta carpeta se encontra la clase **AppDependenciesContainer** esta clase que utiliza el patrón singleton es la encargada de mantener una única instancia de los repositorios que van a ser utilizados a lo largo de la aplicación.

## framework

En esta carpeta se encuentran las implementaciones de los distintos dataSources así como las clases necesarias para comunicarse con las APIs utilizando **Retrofit** (**network**) y la definición de las entidades de la base de datos local del dispositivo (**room**).

Adicionalmente en esta carpeta también se encuentra la carpeta **utils** la cual contiene métodos auxiliares, constantes, etc. que son utilizados para evitar duplicar código a lo largo de la aplicación.

## presentation

En esta carpeta es donde se definen los Activities, Fragments, ViewModels y demás clases que son referentes a la interfaz.

> **Nota**: En esta carpeta también se encuetnra la clase **ViewModelFactory** que de manera similar a la clase **AppDependenciesContainer** se encarga de centralizar en un único lugar la creación de los ViewModel pero además de esto esta clase se utiliza en conjunto con la clase **ViewModelProvider** que permite la creación de ViewModels para un alcance predeterminado; la principal ventaja de esto es que se compartir ViewModels entre un Activity madre y sus fragments hijos.

# Pruebas Unitarias

Para poder probar el funcionamiento de la aplicación se desarrollaron algunas pruebas unitarias para la validación de las implementaciones de los datasources y los procesos de los repositorios.

Por falta de tiempo no se agregaron pruebas para los distintos ViewModels y pruebas automatizadas para la interfaz, otra limitante de estas pruebas es que como no se contaba con una API de prueba no se pudo probar el comportamiento de la aplicación ante distintos códigos de error que no fueran los más comunes retornados por la aplicación.

# Librerías de terceros

## Retrofit
Se utiliza la librería de [Retrofit](https://square.github.io/retrofit/) para simplificar la comunicación con las distintas APIs

## RxJava
Se utiliza la librería de [RxJava](https://github.com/ReactiveX/RxJava) para poder simplificar el manejo de los threads durante el desarrollo de la aplicación.

## Picasso
Se utiliza la Librería de [Picasso](https://square.github.io/picasso/) para facilitar la carga de imágenes ya que permite cargar las imágenes en un ImageView pasando un URL. 

# Inicialización del entorno

Para poder compilar la solución se deben setear las siguientes variables de entorno que son utilizadas para la configuración de la APP así como en las pruebas unitarias:

## Variables de configuración de la APP

- **MELI_API_URL**: URL de la API de MercadoLibre

- **MELI_API_SITE**: Código válido del sitio a utilizar por defecto en la APP

- **MELI_HIDDEN_FILTERS**: Códigos de los filtros que no deben mostrarse al usuario (formato: param1|param2|...paramN|)

### Variables del entorno de pruebas

- **TEST_VALID_API_SITE**: Código de sitio válido a utilizar en las pruebas unitarias

- **TEST_INVALID_API_SITE**: Código de sitio inválido a utilizar en las pruebas unitarias

- **TEST_VALID_ITEM_ID**: Código de artículo válido a utilizar en las pruebas unitarias

- **TEST_INVALID_ITEM_ID**: Código de artículo inválido a utilizar en las pruebas unitarias

- **TEST_VALID_SEARCH_QUERY**: Query de búsqueda válida a utilizar en las pruebas unitarias

- **TEST_INVALID_SEARCH_QUERY**: Query de búsqueda inválido a utilizar en las pruebas unitarias

- **TEST_VALID_FILTER_NAME_1**: Nombre del filtro 1 de búsqueda a utilizar en las pruebas unitarias (válido)

- **TEST_VALID_FILTER_VALUE_1:** Valor del filtro 1 de búsqueda a utilizar en las pruebas unitarias (válido)

- **TEST_VALID_FILTER_NAME_2:** Nombre del filtro 2 de búsqueda a utilizar en las pruebas unitarias (válido)

- **TEST_VALID_FILTER_VALUE_2:** Valor del filtro 1 de búsqueda a utilizar en las pruebas unitarias (válido)

## Scripts de inicialización

Para facilitar la inicialización de estas variables se creo un script en ***InitializationScripts/zsh/initializeTestEnv.sh*** el cual inicializa todas estas variables con valores de prueba apuntando a la API pública de MercadoLibre (https://api.mercadolibre.com/), este script se realizó para utilizar el shell **zsh** y guarda crea las variables en el archivo **.zprofile** para que esten disponibles para todos los shells del usuario.

> Nota: si se tenía Android Studio abierto cuando se corrió este script se deberá cerrar y volver a abrir para que detecte las nuevas variables de entorno.

En las pruebas unitarias de la aplicación se verifica que todas estas variables esten inicializadas con algún valor.