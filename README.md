# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

## Project Description
Is a project that store and   manage word relation; through some
API

### Guides

Instructions
1. Run mvn clean install to install all the dependency
2. Where to change db connection
3.  Access application properties and changes url port username and password


4. To run project  cd   mvn package



Follow the instruction in the Api document to run the api calls

## Entities
1. Words -> stores word
2. WordRelation -> stores word relations 


WordController is used to manage words api
1.   **/api/v1/word/v1/create-word-relation** -> used to get create word relation
2. **/api/v1/word/v1/all**  -> used to get word relation
3. **/api/v1/word/filter/{relation}**  -> used to filter word by  relation
4. **/api/v1/word/searchPath/**  -> used to searchPath



## Apis
Kindly use swagger for api reference
http://localhost:8080/swagger-ui/index.html#/
