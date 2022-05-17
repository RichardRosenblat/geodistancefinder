# Geo Distance Finder
Essa api permite que o usuário entregue uma quantidade de endereços e descubra a distancia euclidiana entre eles e ache os endereços mais proximos.

Ela utiliza a api de geocoding da google cloud para buscar as coordenadas geograficas dos endereços dados e retorna as distancias entre todas as combinações possiveis entre eles.

## Como instalar o projeto
 1. Clone o projeto
 2. Crie um arquivo chamado `applicationSecret.properties` na pasta `\src\main\resources`
    1. Adicione as seguinte linhas:
            
            google.url=https://maps.googleapis.com
            google.key={key}

    2. Substitua `{key}` por uma chave de API criada na plataforma do google cloud (Mais informações [aqui](https://developers.google.com/maps/documentation/geocoding/get-api-key))

3. Rode o comando mvn install no bash para que as dependencias sejam instaladas
            
        > mvn install
## Endpoints

Essa api possui apenas um endpoint.

### Get distance

#### Request

    GET http://localhost:8080/distance/?addresses={params}

Substitua `{params}` por endereços separados por `;` ou `;%20` qualquer espaço em branco deve ser substituido pelo charactere `%20`<br>
Exemplo:

    277%20Bedford%20Ave,%20Brooklyn,%20NY%2011211,%20EUA;Praça%20dos%20Três%20Poderes%20-%20Brasília,%20DF,%2070150-900;R.%20Natingui,%20862%20-%20Vila%20Madalena,%20São%20Paulo%20-%20SP,%2005443-001

#### Response:
```json
{
    "closestMatch": [
        {
            "firstAddress": {
                "address": "R. Natingui, 862 - Vila Madalena, São Paulo - SP, 05443-001",
                "coordinates": {
                    "latitude": -23.5539105,
                    "longitude": -46.6970842
                }
            },
            "secondAddress": {
                "address": "Praça dos Três Poderes - Brasília, DF, 70150-900",
                "coordinates": {
                    "latitude": -15.8003048,
                    "longitude": -47.8626804
                }
            },
            "distance": 7.840728018017648
        }
    ],
    "furthestMatch": [
        {
            "firstAddress": {
                "address": "277 Bedford Ave, Brooklyn, NY 11211, EUA",
                "coordinates": {
                    "latitude": 40.7142205,
                    "longitude": -73.9612903
                }
            },
            "secondAddress": {
                "address": "R. Natingui, 862 - Vila Madalena, São Paulo - SP, 05443-001",
                "coordinates": {
                    "latitude": -23.5539105,
                    "longitude": -46.6970842
                }
            },
            "distance": 69.81210207762291
        }
    ],
    "results": [
        {
            "firstAddress": {
                "address": "277 Bedford Ave, Brooklyn, NY 11211, EUA",
                "coordinates": {
                    "latitude": 40.7142205,
                    "longitude": -73.9612903
                }
            },
            "secondAddress": {
                "address": "Praça dos Três Poderes - Brasília, DF, 70150-900",
                "coordinates": {
                    "latitude": -15.8003048,
                    "longitude": -47.8626804
                }
            },
            "distance": 62.24973099216348
        },
        {
            "firstAddress": {
                "address": "277 Bedford Ave, Brooklyn, NY 11211, EUA",
                "coordinates": {
                    "latitude": 40.7142205,
                    "longitude": -73.9612903
                }
            },
            "secondAddress": {
                "address": "R. Natingui, 862 - Vila Madalena, São Paulo - SP, 05443-001",
                "coordinates": {
                    "latitude": -23.5539105,
                    "longitude": -46.6970842
                }
            },
            "distance": 69.81210207762291
        },
        {
            "firstAddress": {
                "address": "R. Natingui, 862 - Vila Madalena, São Paulo - SP, 05443-001",
                "coordinates": {
                    "latitude": -23.5539105,
                    "longitude": -46.6970842
                }
            },
            "secondAddress": {
                "address": "Praça dos Três Poderes - Brasília, DF, 70150-900",
                "coordinates": {
                    "latitude": -15.8003048,
                    "longitude": -47.8626804
                }
            },
            "distance": 7.840728018017648
        }
    ]
}
```


