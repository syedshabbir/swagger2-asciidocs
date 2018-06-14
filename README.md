# swagger2-asciidocs-pdf-html-generator

You can run the following to generate offline docs

```
> .\gradlew clean generateSwaggerDocumentation asciidoctor
```

content built to swagger2-asciidocs-pdf-html-generator/build/asciidoc. PDF content in 'pdf' directory and 'HTML5' content in 'html5' directory.

And to view docs online

Use the [script](scripts/create-mapping-table.sql) to create a Postgres table and update [yaml file](src/main/resources/application.yml) with your datasource values.

Run commands below

```
>  .\gradlew clean generateSwaggerDocumentation asciidoctor build
> java -jar .\build\libs\swagger2-asciidocs-pdf-html-generator-1.0.jar
```
Visit http://localhost:8080/index.html and/or http://localhost:8080/index.pdf

For detailed information look at https://www.linkedin.com/pulse/using-gradle-generate-swagger-asciidocs-pdfhtml5-offline-syed-shabbir for instructions
