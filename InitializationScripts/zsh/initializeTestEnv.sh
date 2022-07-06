# URL de la API de MercadoLibre
MELI_API_URL=https://api.mercadolibre.com/
# Código válido del sitio a utilizar por defecto en la APP
MELI_API_SITE=MLU

# Valores variables de entorno para definir el entorno de pruebas
# Código de sitio válido a utilizar en las pruebas unitarias
TEST_VALID_API_SITE=MLU
# Código de sitio inválido a utilizar en las pruebas unitarias
TEST_INVALID_API_SITE=La-Li-Lu-Le-Lo
# Código de artículo válido a utilizar en las pruebas unitarias
TEST_VALID_ITEM_ID=MLU600098464
# Código de artículo inválido a utilizar en las pruebas unitarias
TEST_INVALID_ITEM_ID=La-Li-Lu-Le-Lo
# Query de búsqueda válida a utilizar en las pruebas unitarias
TEST_VALID_SEARCH_QUERY='oculus quest'
# Query de búsqueda inválido a utilizar en las pruebas unitarias
TEST_INVALID_SEARCH_QUERY=La-Li-Lu-Le-Lo
# Nombre del filtro 1 de búsqueda a utilizar en las pruebas unitarias (válido)
TEST_VALID_FILTER_NAME_1=sort
# Valor del filtro 1 de búsqueda a utilizar en las pruebas unitarias (válido)
TEST_VALID_FILTER_VALUE_1=price_desc
# Nombre del filtro 2 de búsqueda a utilizar en las pruebas unitarias (válido)
TEST_VALID_FILTER_NAME_2=power_seller
# Valor del filtro 1 de búsqueda a utilizar en las pruebas unitarias (válido)
TEST_VALID_FILTER_VALUE_2=yes

# Busca si la variable MELI_API_URL ya se haya definido en el entorno, sino la inserta
if grep 'export MELI_API_URL=.*' ~/.zprofile; then
    sed -i -e "s|export MELI_API_URL=.*|export MELI_API_URL=$MELI_API_URL|" ~/.zprofile 
else
    echo "export MELI_API_URL=$MELI_API_URL" >> ~/.zprofile;
fi

# Busca si la variable MELI_API_SITE ya se haya definido en el entorno, sino la inserta
if grep 'export MELI_API_SITE=.*' ~/.zprofile; then
    sed -i -e "s|export MELI_API_SITE=.*|export MELI_API_SITE=$MELI_API_SITE|" ~/.zprofile 
else
    echo "export MELI_API_SITE=$MELI_API_SITE" >> ~/.zprofile;
fi

# Busca si la variable TEST_VALID_API_SITE ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_VALID_API_SITE=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_VALID_API_SITE=.*|export TEST_VALID_API_SITE=$TEST_VALID_API_SITE|" ~/.zprofile 
else
    echo "export TEST_VALID_API_SITE=$TEST_VALID_API_SITE" >> ~/.zprofile;
fi

# Busca si la variable TEST_INVALID_API_SITE ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_INVALID_API_SITE=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_INVALID_API_SITE=.*|export TEST_INVALID_API_SITE=$TEST_INVALID_API_SITE|" ~/.zprofile 
else
    echo "export TEST_INVALID_API_SITE=$TEST_INVALID_API_SITE" >> ~/.zprofile;
fi

# Busca si la variable TEST_VALID_ITEM_ID ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_VALID_ITEM_ID=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_VALID_ITEM_ID=.*|export TEST_VALID_ITEM_ID=$TEST_VALID_ITEM_ID|" ~/.zprofile 
else
    echo "export TEST_VALID_ITEM_ID=$TEST_VALID_ITEM_ID" >> ~/.zprofile;
fi

# Busca si la variable TEST_INVALID_ITEM_ID ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_INVALID_ITEM_ID=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_INVALID_ITEM_ID=.*|export TEST_INVALID_ITEM_ID=$TEST_INVALID_ITEM_ID|" ~/.zprofile 
else
    echo "export TEST_INVALID_ITEM_ID=$TEST_INVALID_ITEM_ID" >> ~/.zprofile;
fi

# Busca si la variable TEST_VALID_SEARCH_QUERY ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_VALID_SEARCH_QUERY=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_VALID_SEARCH_QUERY=.*|export TEST_VALID_SEARCH_QUERY='$TEST_VALID_SEARCH_QUERY'|" ~/.zprofile 
else
    echo "export TEST_VALID_SEARCH_QUERY=$TEST_VALID_SEARCH_QUERY" >> ~/.zprofile;
fi

# Busca si la variable TEST_INVALID_SEARCH_QUERY ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_INVALID_SEARCH_QUERY=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_INVALID_SEARCH_QUERY=.*|export TEST_INVALID_SEARCH_QUERY=$TEST_INVALID_SEARCH_QUERY|" ~/.zprofile 
else
    echo "export TEST_INVALID_SEARCH_QUERY=$TEST_INVALID_SEARCH_QUERY" >> ~/.zprofile;
fi

# Busca si la variable TEST_VALID_FILTER_NAME_1 ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_VALID_FILTER_NAME_1=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_VALID_FILTER_NAME_1=.*|export TEST_VALID_FILTER_NAME_1=$TEST_VALID_FILTER_NAME_1|" ~/.zprofile 
else
    echo "export TEST_VALID_FILTER_NAME_1=$TEST_VALID_FILTER_NAME_1" >> ~/.zprofile;
fi

# Busca si la variable TEST_VALID_FILTER_VALUE_1 ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_VALID_FILTER_VALUE_1=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_VALID_FILTER_VALUE_1=.*|export TEST_VALID_FILTER_VALUE_1=$TEST_VALID_FILTER_VALUE_1|" ~/.zprofile 
else
    echo "export TEST_VALID_FILTER_VALUE_1=$TEST_VALID_FILTER_VALUE_1" >> ~/.zprofile;
fi

# Busca si la variable TEST_VALID_FILTER_NAME_2 ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_VALID_FILTER_NAME_2=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_VALID_FILTER_NAME_2=.*|export TEST_VALID_FILTER_NAME_2=$TEST_VALID_FILTER_NAME_2|" ~/.zprofile 
else
    echo "export TEST_VALID_FILTER_NAME_2=$TEST_VALID_FILTER_NAME_2" >> ~/.zprofile;
fi

# Busca si la variable TEST_VALID_FILTER_VALUE_2 ya se haya definido en el entorno, sino la inserta
if grep 'export TEST_VALID_FILTER_VALUE_2=.*' ~/.zprofile; then
    sed -i -e "s|export TEST_VALID_FILTER_VALUE_2=.*|export TEST_VALID_FILTER_VALUE_2=$TEST_VALID_FILTER_VALUE_2|" ~/.zprofile 
else
    echo "export TEST_VALID_FILTER_VALUE_2=$TEST_VALID_FILTER_VALUE_2" >> ~/.zprofile;
fi