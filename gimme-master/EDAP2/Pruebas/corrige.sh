#! /bin/bash

total=0
diferencias=""

function CorrigeResto(){
    nombre=$1

    diff -w $nombre.tmp $nombre.txt > d1.tmp
    nlin=$(cat d1.tmp|wc -l)
    if test $nlin -eq 0; then
      nota=1
    else
      nota=0
      diferencias="diferencias en fichero salida txt; ejecuta diff -w $nombre.txt $nombre.tmp"
    fi
    rm -rf d?.tmp  
    return $nota
}


compilador=javac

interprete=java


fuentes=$(ls *.java 2>/dev/null)

directorio=practica2-prueba
javas=$(ls *.java|wc -l)
numfuentes=0
nota=0
rm -rf *.tmp  $directorio/*.tmp $directorio/*.terr *.class $directorio/*.class $directorio/*.tmp.err

for fichero in $fuentes; do
 if test $fichero == Coordenada.java || test $fichero == CoordenadaExcepcion.java || \
 test $fichero == PLoc.java || test $fichero == CiudadNoEncontradaExcepcion.java || \
 test $fichero == Lista.java || test $fichero == ListaG.java || test $fichero == VectorG.java || \
 test $fichero == BuscaLocalizacion.java; then 
  if test -f $fichero; then
  let numfuentes=numfuentes+1
  fi
 fi
done

if test $numfuentes -eq 8 -a $javas -eq 8; then
  continuar=true
else
  continuar=false
  echo "Error, ficheros fuente requeridos: Coordenada.java, CoordenadaExcepcion.java, PLoc.java, Lista.java, ListaG.java, VectorG.java, CiudadNoEncontradaExcepcion.java y BuscaLocalizacion.java; 0"
fi
if $continuar; then
 $compilador *.java 2> errores.compilacion 
 numlin=$(cat errores.compilacion | wc -l)
 if test $numlin -ne 0; then
  echo "Error de compilacion; 0"
  exit 1
 else
  rm -rf errores.compilacion
 fi
 # rm -rf errores.compilacion
 mv *.class $directorio
 cd  $directorio
 ficherosprueba=$(ls *.java)
 total=0
 for prueba in $ficherosprueba; do
  nombre=$(basename $prueba .java)
  $compilador $prueba 2> $nombre.terr 
  numlin=$(cat $nombre.terr | wc -l)
  if test $numlin -eq 0; then
   if test -e $nombre.dat; then
    $interprete $nombre $nombre.dat >$nombre.tmp 2>$nombre.tmp.err
   else
    $interprete $nombre >$nombre.tmp 2>$nombre.tmp.err
   fi
    numlin=$(cat $nombre.tmp.err|wc -l)
    if test $numlin -eq 0; then
      CorrigeResto $nombre
      nota=$?
      if test $nota -eq 1; then
        echo "Prueba $nombre: Ok"
        total=$(echo "$total+0.5"|bc)
      else
        echo "Prueba $nombre: $diferencias" 
      fi
    else
      echo "Prueba $nombre: Hay errores de ejecucion"
      cat $nombre.tmp.err
    fi
  else
   echo "Prueba $nombre: hay errores de compilacion" 
   cat $nombre.terr
  fi
 rm -rf d1.tmp $nombre.terr 
 done
 echo "Nota: $total"
fi

