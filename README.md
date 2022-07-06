# Android App

## Inicialización del entorno

Para poder compilar la solución se deben setear las siguientes variables de entorno que son utilizadas para la configuración de la APP así como en las pruebas unitarias:

### Variables de configuración de la APP

- **MELI_API_URL**: URL de la API de MercadoLibre

- **MELI_API_SITE**: Código válido del sitio a utilizar por defecto en la APP

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

### Scripts de inicialización

Para facilitar la inicialización de estas variables se creo un script en ***InitializationScripts/zsh/initializeTestEnv.sh*** el cual inicializa todas estas variables con valores de prueba apuntando a la API pública de MercadoLibre (https://api.mercadolibre.com/), este script se realizó para utilizar el shell **zsh** y guarda crea las variables en el archivo **.zprofile** para que esten disponibles para todos los shells del usuario.

> Nota: si se tenía Android Studio abierto cuando se corrió este script se deberá cerrar y volver a abrir para que detecte las nuevas variables de entorno.

En las pruebas unitarias de la aplicación se verifica que todas estas variables esten inicializadas con algún valor.

## Retrofit
Se utiliza la librería de [Retrofit](https://square.github.io/retrofit/) para simplificar la comunicación con las distintas APIs

## RxJava
Se utiliza la librería de [RxJava](https://github.com/ReactiveX/RxJava) para poder simplificar el manejo de los threads durante el desarrollo de la aplicación.