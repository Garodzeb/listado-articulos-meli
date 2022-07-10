# Android App

Esta solución se compone de dos proyectos:

- **app**: Este proyecto es donde se encuentra el código nativo de la aplicación y el código utilizado en la comunicación con las APIs.

- **MeliSearchCore**: Este proyeto contiene las clases del dominio de la aplicación donde se definen las reglas de negocio.

# MeliSearchCore

## datasource_abstractions

En esta carpeta se enuentran las interfaces que definen los dataSources que utilizan los repositorios para definir los distintos procesos de la aplicación, en esta carpeta se provee una interfaz tanto para los repositorios locales como remotos.

> Se define como datasource local a las clases que se encargan de manipular información directamente desde el dispositivo y en cambio un datasource remoto son las clases que se debe comunicar con las APIs y no trabajan con los datos almacenados en el dispositivo.

## model

En esta carpeta se definen todos los objetos del dominio utilizados a lo largo de la aplicación.

> Cabe resaltar que estas clases no guardan una relación 1:1 con los datos retornados por las APIs sino que contienen exclusivamente los campos que son necesarios para cumplir con la lógica de la aplicación

## repositories

En esta carpeta se encuentran los repositorios, cada repositorio se encarga de definir la comunicación con los dataSources y es responsable de seleccionar si se va a utilizar un datasource remoto o local para cumplir con los requisitos de la interfaz y son la única manera que tiene la aplicación de comunicarse con los datasources.

# app

## application

En esta carpeta se encontra la clase **AppDependenciesContainer** esta clase (que utiliza el patrón singleton) se encarga de mantener una única instancia de los repositorios que van a ser utilizados por los distintos ViewModels a lo largo de la aplicación.

## framework

En esta carpeta se encuentran las implementaciones de los distintos dataSources, para simplificar la comunicación con las APIs se utiliza la librería de **Retrofit** y para el manejo de la base de datos interna de la aplicación se utilizan las librearías de **Room** provistas por el sdk de Android.

Adicionalmente en esta carpeta también se encuentra la carpeta **utils** la cual contiene métodos auxiliares, constantes, etc. que son utilizados para evitar duplicar código a lo largo de la aplicación.

## presentation

En esta carpeta es donde se definen los Activities, Fragments, ViewModels y demás clases que se encargan de manejar el comportamiento de la interfaz.

> **Nota**: En esta carpeta también se encuetnra la clase **ViewModelFactory** que de manera similar a la clase **AppDependenciesContainer** se encarga de centralizar en un único lugar la creación de los ViewModel, esta clase se utiliza en conjunto con la clase **ViewModelProvider** para permitir la creación de ViewModels para un alcance predeterminado; la principal ventaja de esto es que se pueden compartir información entre un Activity madre y sus fragments hijos por medio del ViewModel común.

# Pruebas Unitarias

Para poder probar el funcionamiento de la aplicación se desarrollaron algunas pruebas unitarias para la validación de las implementaciones de los datasources y los procesos de los repositorios.

Por falta de tiempo no se agregaron pruebas para los distintos ViewModels y pruebas automatizadas para la interfaz, otra limitante de estas pruebas es que como no se contaba con una API de prueba no se pudo probar el comportamiento de la aplicación ante distintos códigos de error que no fueran los más comunes retornados por la aplicación.

# Manejo de errores

En el caso de que la aplicación encuentre un error inesperado estos seran logueados en la consola del dispositivo y se le mostrara una pantalla de error al usuario, en general nunca se debería mostrar el error al usuario y nunca se debería caer la aplicación por un error inesperado.

# Librerías de terceros

## Retrofit
Se utiliza la librería de [Retrofit](https://square.github.io/retrofit/) para simplificar la comunicación con las distintas APIs

## RxJava
Se utiliza la librería de [RxJava](https://github.com/ReactiveX/RxJava) para poder simplificar el manejo de los threads durante el desarrollo de la aplicación.

## Picasso
Se utiliza la Librería de [Picasso](https://square.github.io/picasso/) para facilitar la carga de imágenes de los artículos ya que permite cargar las imágenes en un ImageView pasando un URL.

> Debido a que las imágenes de los artículos estan expuestas por Http se tuvo que modificar el manifest de la aplicación para permitir trafico por Http sin encriptar (android:usesCleartextTraffic="true").

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

Para facilitar la inicialización de estas variables se creo un script en ***InitializationScripts/zsh/initializeTestEnv.sh*** el cual inicializa todas estas variables con valores de prueba apuntando a la API pública de MercadoLibre (https://api.mercadolibre.com/), este script se realizó para utilizar el shell **zsh** y agrega/modifica las variables en el archivo **.zprofile** para que esten disponibles para todos los shells del usuario.

> **Nota**: si se tenía Android Studio abierto cuando se corrió este script se deberá cerrar y volver a abrir para que detecte las nuevas variables de entorno.

En las pruebas unitarias de la aplicación se verifica que todas estas variables esten inicializadas con algún valor.

> Debido a que esta es una aplicación de prueba todas estas variables tienen definido un valor por defecto en el caso de que no se encuentren las variables de entorno, estos valores estan definidos en el archivo **./app/build.graddle**