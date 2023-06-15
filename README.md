[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/teoria1-grupo7"><img src="src/gui/unlu.png" alt="Logo" width="160" height="160"></a>
  <h4 align="center">Universidad Nacional de Luján</h4>
  <h1 align="center">Teoría de la Computación I (11412)</h1>
  <h2 align="center">Compilador - Trabajo Práctico Integrador</h2>  
  <h3 align="center">Curso 2020</h3>
  <p align="center"><a href="https://github.com/teoria1-grupo7"><strong>Explorar los documentos »</strong></a></p>
</p>



<!-- INDICE -->
## Indice
* [Equipo docente](#equipo-docente)
* [Integrantes del grupo](#integrantes-del-grupo)
* [Instalación y ejecución](#instalacion-y-ejecucion)



<!-- DOCENTES -->
## Equipo docente
| Profesor  | Cargo  |
| :------------ |:---------------:|
| Mara Capuya      | Titular |
| Silvia Cuagliarelli      | JTP        |
| Eugenia Céspedes | Ayudante        |



<!-- INTEGRANTES -->
## Integrantes del grupo
| Alumno  | Legajo  |
| :------------ |:---------------:|
| César Ferrarotti      | 113145 |
| Mariano Rapaport | 155671        |



<!-- INSTALACION -->
## Instalacion y ejecucion
 * Clonar el repositorio en IntelliJ IDEA: `https://github.com/teoria1-grupo7/compilador.git`.
 * Ejecutar el target `run` del `build.xml` con Apache Ant para generar nuevamente las clases de JFlex y JCUP.
 * Puede ejecutarse la clase `compilador/Main.java` para visualizar la salida por terminal, y la clase `gui/MainFrame.java` para la interfaz gráfica.
 * Se puede utilizar la versión ya compilada `bin/compilador.jar`.
 
 ## Ejecución del compilador
Una vez iniciada la GUI, podemos escribir directamente el código a compilar en el panel lateral
izquierdo, o bien abrirlo seleccionando el archivo desde la opción "Abrir" en el menú superior. 

A continuación, clickeamos el botón "Compilar" del panel superior. Como resultado, en la pestaña "Salida"
del panel lateral derecho veremos la interpretación de la gramática conforme el _parser_ recorrió las intrucciones.
Si la compilación fue exitosa, se verá un mensaje en pantalla indicandolo y un menú de selección de archivo
se abrirá para elegir la ubicación del archivo .asm generado. En la tercera pestaña del panel derecho
también podremos ver esta salida. 
 
 <!-- MARKDOWN LINKS & IMAGES -->
 <!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
 [contributors-shield]: https://img.shields.io/github/contributors/teoria1-grupo7/compilador.svg?style=flat-square
 [contributors-url]: https://github.com/teoria1-grupo7/compilador/graphs/contributors
 [forks-shield]: https://img.shields.io/github/forks/teoria1-grupo7/compilador.svg?style=flat-square
 [forks-url]: https://github.com/teoria1-grupo7/compilador/network/members
 [stars-shield]: https://img.shields.io/github/stars/teoria1-grupo7/compilador.svg?style=flat-square
 [stars-url]: https://github.com/teoria1-grupo7/compilador/stargazers
 [issues-shield]: https://img.shields.io/github/issues/teoria1-grupo7/compilador.svg?style=flat-square
 [issues-url]: https://github.com/teoria1-grupo7/compilador/issues
 