# Android App

## Variables de entorno

Para poder compilar la aplicación se deben crear/setear las siguientes variables de entorno:

- MELI_API_URL -> Url base utilizada para llamar a las APIs de MercadoLibre, en caso de no encontrar la variable la aplicación utilizara la siguiente URL = https://api.mercadolibre.com/

- MELI_API_SITE -> Código del sitio a utilizar para las llamadas de la API, en caso de no encontrar la variable la aplicación utilizara el valor "MLU"

## Retrofit
Se utiliza la librería de Retrofit para simplificar la comunicación con las distintas APIs

## RxJava
Se utiliza la librería de RxJava para poder simplificar el manejo de los threads durante el desarrollo de la aplicación.