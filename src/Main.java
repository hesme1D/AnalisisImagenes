import analisisespacial.FiltrosEspaciales;
import analisisfrecuencias.FiltroButterworthPasaBajas;
import analisisfrecuencias.FiltroButterworthPasaAltas;
//import analisisfrecuencias.FiltroIdealPasaAltas;
import analisisfrecuencias.FiltroGaussiano;
import open.AbrirImagen;
import java.awt.Image;
import analisisfrecuencias.Gestor;
import gui.JframeImagen;
import java.awt.image.BufferedImage;
import analisisfrecuencias.FiltroIdealPasaBajas;
import analisisfrecuencias.FiltroSelectivo;
import analisisfrecuencias.NumeroComplejo;
import gui.JFrameIm;
import java.awt.Dimension;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        
//          //PRUEBAS
          int[][] puntos;
          Image io = AbrirImagen.openImage();
          //Image gris = FiltrosEspaciales.generarImagenGirs(io);
          int escala = 512;
          
          io = AbrirImagen.toBufferedImage(io).getScaledInstance(escala, escala, BufferedImage.TYPE_INT_BGR);
          JFrameIm frame = new JFrameIm(io);
          
          Gestor gest = new Gestor(AbrirImagen.toBufferedImage(io));
          BufferedImage frec = gest.obtenerImagenFrecuencias(true);
          Image fre = AbrirImagen.toImage(frec);
          JFrameIm frame2 = new JFrameIm(fre, true);
          
          do{
              System.err.println("En espera...");
              sleep(100);
          }while(frame2.getLista().size()<3);
          puntos = convertIntegers(frame2.getLista());
          
          FiltroSelectivo selec = new FiltroSelectivo(10, new Dimension(escala,escala),puntos); 
          selec.crearFiltro();
          NumeroComplejo[][] filtro = selec.getFiltroEspacial();
          JFrameIm f = new JFrameIm(selec.getImage());
          gest.aplicarFiltro(filtro);
          Image fin = AbrirImagen.toImage(gest.obtenerImagenEspacial());
          JFrameIm fr = new JFrameIm(fin);
          
          Image Fq = AbrirImagen.toImage(gest.obtenerImagenFrecuencias(true));
          JFrameIm idk = new JFrameIm(Fq);
          
          Random ran = new Random();
          int aux = ran.nextInt();
          
          AbrirImagen.GuardarImagen(io, "Original"+aux);
          AbrirImagen.GuardarImagen(fre, "Espectro"+aux);
          AbrirImagen.GuardarImagen(selec.getImage(), "Filtro"+aux);
          AbrirImagen.GuardarImagen(fin, "Imagen Filtrada"+aux);
          AbrirImagen.GuardarImagen(Fq, "Espectro Filtrado"+aux);
          System.out.println("Listo");


//        Image imagen = AbrirImagen.openImage();
//        Image gris = FiltrosEspaciales.generarImagenGirs(imagen);
//        JframeImagen frame = new JframeImagen(gris);
//        Gestor gestor = new Gestor(AbrirImagen.toBufferedImage(gris));
//        BufferedImage bf = gestor.obtenerImagenFrecuencias(true);
//        JframeImagen frame2 = new JframeImagen(AbrirImagen.toImage(bf));
        //Creamos el filtro
        //FILTRO PASA ALTAS
        //FiltroIdealPasaAltas fipa = new FiltroIdealPasaAltas(10,new Dimension(512,512));
        //fipa.crearFiltro();
        //NumeroComplejo[][] filtro = fipa.getFiltroEspacial();
        //JframeImagen frameFiltro = new JframeImagen(fipa.getImagen());
        //gestor.aplicarFiltro(filtro);
        
        //FILTRO PASA BAJAS
        //FiltroIdealPasaBajas fipb = new FiltroIdealPasaBajas(10,new Dimension(512,512));
        //fipb.crearFiltro();
        //NumeroComplejo[][] f2 = fipb.getFiltroEspacial();
        //JframeImagen frameFiltro2 = new JframeImagen(fipb.getImagen());
        //gestor.aplicarFiltro(f2);
        
        //BufferedImage imEsp = gestor.obtenerImagenEspacial();
        //JframeImagen frame3 = new JframeImagen(AbrirImagen.toImage(imEsp));
        //Histogramas h1 = new Histogramas(imagen);
        //h1.graficarHistogramas();
        
        //FILTRO BUTTERWORTH PASA BAJAS
        //FiltroButterworthPasaBajas fbpb = new FiltroButterworthPasaBajas(new Dimension(512,512),50,50);
        //fbpb.crearFiltro();
        //NumeroComplejo[][] f3 = fbpb.getFiltroEspacial();
        //JframeImagen frameFiltro3 = new JframeImagen(fbpb.getImagen());
        //gestor.aplicarFiltro(f3);
        
        //BufferedImage imEsp2 = gestor.obtenerImagenEspacial();
        //JframeImagen fr3 = new JframeImagen(AbrirImagen.toImage(imEsp2));
        
        //FILTRO BUTTERWORTH PASA ALTAS
//        FiltroButterworthPasaAltas fbpa = new FiltroButterworthPasaAltas(new Dimension(512,512),100,50);
//        fbpa.crearFiltro();
//        NumeroComplejo[][] f4 = fbpa.getFiltroEspacial();
//        JframeImagen frameFiltro4 = new JframeImagen(fbpa.getImagen());
//        gestor.aplicarFiltro(f4);
//        
//        BufferedImage imEsp3 = gestor.obtenerImagenEspacial();
//        JframeImagen fr4 = new JframeImagen(AbrirImagen.toImage(imEsp3));
        
        //FILTRO GAUSSIANO
//        FiltroGaussiano fg = new FiltroGaussiano(new Dimension(512,512),5,false);
//        fg.crearFiltro();
//        NumeroComplejo[][] f4 = fg.getFiltroEspacial();
//        JframeImagen frameFiltro4 = new JframeImagen(fg.getImagen());
//        gestor.aplicarFiltro(f4);
//        
//        BufferedImage imEsp3 = gestor.obtenerImagenEspacial();
//        JframeImagen fr4 = new JframeImagen(AbrirImagen.toImage(imEsp3));

        //Image ruidoA = Suavizado.agregarRuidoAditivo(imagen, 30);
        //JframeImagen ira = new JframeImagen(ruidoA);
        //Histogramas hra = new Histogramas(ruidoA);
        //hra.graficarHistogramas();
        
        //Image ruidoS = Suavizado.agregarRuidoSustractivo(imagen, 5);
        //JframeImagen irs = new JframeImagen(ruidoS);
        //Histogramas hrs = new Histogramas(ruidoS);
        //hrs.graficarHistogramas();
        
        //Mezclado
        //Image ruidoAS = Suavizado.agregarRuidosMezclados(ruidoS, 5);
        //JframeImagen iras = new JframeImagen(ruidoAS);
        //Histogramas hras = new Histogramas(ruidoAS);
        //hras.graficarHistogramas();
        
        //int[][] mascara = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        //Image Suav = Suavizado.Suavizar(ruidoS, mascara);
        //JframeImagen is = new JframeImagen(Suav);
        //Histogramas his = new Histogramas(Suav);
        //his.graficarHistogramas();
        
        //Máscara para el enfoque
        //int[][] mascaraEnfoque = new int[][]{{0,0,0,0,0},{0,0,-1,0,0},{0,-1,5,-1,0},{0,0,-1,0,0},{0,0,0,0,0}};
        //Image ConvEnfoque = Convolucion.AplicarConvolucion(imagen, mascaraEnfoque, 1, 0);
        //JframeImagen icE = new JframeImagen(ConvEnfoque);
        //Histogramas hce = new Histogramas(ConvEnfoque);
        //hce.graficarHistogramas();
        
        //Mascara de desenfoque
        //int[][] mascaraDes = new int[][]{{1,1,1},{1,1,1},{1,1,1}};
        //int[][] mascaraDes = new int[][]{{0,0,0,0,0},{0,1,1,1,0},{0,1,1,1,0},{0,1,1,1,0},{0,0,0,0,0}};
        //Image ConvDes = Convolucion.AplicarConvolucion(imagen, mascaraDes, 9, 0);
        //JframeImagen icD = new JframeImagen(ConvDes);
        //Histogramas hcd = new Histogramas(ConvDes);
        //hcd.graficarHistogramas();
        
        //Mascara realzar bordes
        //int[][] mascaraRealce = new int[][]{{0,0,0},{-1,1,0},{0,0,0}};
        //Image ConvRes = Convolucion.AplicarConvolucion(imagen, mascaraRealce, 1, 0);
        //JframeImagen icR = new JframeImagen(ConvRes);
        //Histogramas hcr = new Histogramas(ConvRes);
        //hcr.graficarHistogramas();
        
        //Mascara detectar bordes
        //int[][] mascaraBordes = new int[][]{{0,1,0},{1,-4,1},{0,1,0}};
        //Image ConvBorde = Convolucion.AplicarConvolucion(imagen, mascaraBordes, 1, 0);
        //JframeImagen icB = new JframeImagen(ConvBorde);
        //Histogramas hcb = new Histogramas(ConvBorde);
        //hcb.graficarHistogramas();
        
        //Mascara Repujado
        //int[][] mascaraRep = new int[][]{{-2,-1,0},{-1,1,1},{0,1,2}};
        //Image ConvRep = Convolucion.AplicarConvolucion(imagen, mascaraRep, 1, 0);
        //JframeImagen icRep = new JframeImagen(ConvRep);
        //Histogramas hcre = new Histogramas(ConvRep);
        //hcre.graficarHistogramas();
        
        //Sobel
        //Image gris = FiltrosEspaciales.generarImagenGirs(imagen);
        //JframeImagen igris = new JframeImagen(gris);
        //Histogramas h2 = new Histogramas(gris);
        //h2.graficarHistogramaGrises();
        //double[][] mascaraSobelx = new double[][]{{-1,0,1},{-2,0,2},{-1,0,1}};
        //double[][] mascaraSobely = new double[][]{{-1,-2,-1},{0,0,0},{1,2,1}};
        //Image ConvSobel = Convolucion.Convolucion(gris, mascaraSobelx, 1);
        //JframeImagen icSobel = new JframeImagen(ConvSobel);
        //Histogramas hcS = new Histogramas(ConvSobel);
        //hcS.graficarHistogramas();
        
        //Prewitt
        //Image gris2 = FiltrosEspaciales.generarImagenGirs(imagen);
        //JframeImagen igris2 = new JframeImagen(gris2);
        //Histogramas h3 = new Histogramas(gris2);
        //h3.graficarHistogramaGrises();
        //double[][] mascaraPrewittx = new double[][]{{-1,0,1},{-1,0,1},{-1,0,1}};
        //double[][] mascaraPrewitty = new double[][]{{1,1,1},{0,0,0},{-1,-1,-1}};
        //Image ConvPrewitt = Convolucion.Convolucion(gris2, mascaraPrewitty, 1);
        //JframeImagen icPrewitt = new JframeImagen(ConvPrewitt);
        //Histogramas hcP = new Histogramas(ConvPrewitt);
        //hcP.graficarHistogramas();
        
        //Roberts
        //Image gris3 = FiltrosEspaciales.generarImagenGirs(imagen);
        //JframeImagen igris3 = new JframeImagen(gris3);
        //Histogramas h3 = new Histogramas(gris3);
        //h3.graficarHistogramaGrises();
        //double[][] mascaraRobertsx = new double[][]{{-1,0,0},{0,1,0},{0,0,0}};
        //double[][] mascaraRobertsy = new double[][]{{0,0,-1},{0,1,0},{0,0,0}};
        //Image ConvRoberts = Convolucion.Convolucion(gris3, mascaraRobertsy, 1);
        //JframeImagen icRoberts = new JframeImagen(ConvRoberts);
        //Histogramas hcR = new Histogramas(ConvRoberts);
        //hcR.graficarHistogramas();
        
        //Generar imagen en grises
        //Image gris = FiltrosEspaciales.generarImagenGirs(imagen);
        //JframeImagen igris = new JframeImagen(gris);
        //Histogramas h2 = new Histogramas(gris);
        //h2.graficarHistogramaGrises();
        //Image e = FiltrosEspaciales.Ecualizar(gris);
        //JframeImagen frame2 = new JframeImagen(e);
        //Histogramas he = new Histogramas(e);
        //he.graficarHistogramaGrises();
        
        //Implementar un método interativo
        //int u = FiltrosEspaciales.metodoIterativo(h2.getHBlue());
        //Image bin = FiltrosEspaciales.Binarizar(gris, u);
        //JframeImagen bin1 = new JframeImagen(bin);
        //Histogramas h3 = new Histogramas(bin);
        //h3.graficarHistogramas();
        
        //Expansión lineal
        //Histogramas h = new Histogramas(imagen); 
        //h.graficarHistogramas();
        //JframeImagen fi = new JframeImagen(imagen);
        //Image contraste = FiltrosEspaciales.contraste(h, imagen);
        //JframeImagen fic = new JframeImagen(contraste);
        //Histogramas h1 = new Histogramas(contraste);
        //h1.graficarHistogramas();
        //Expansión Exponencial
        //Image exp = FiltrosEspaciales.Exponencial(imagen, 100);
        //JframeImagen frame = new JframeImagen(exp);
        //Histogramas h2 = new Histogramas(exp);
        //h2.graficarHistogramas();
        //Expansión Logaritmica
        //Image log = FiltrosEspaciales.Logaritmica(imagen);
        //JframeImagen frame2 = new JframeImagen(log);
        //Histogramas h3 = new Histogramas(log);
        //h3.graficarHistogramas();
        
        //Formula Propia
        //Image p = FiltrosEspaciales.Propia(imagen);
        //JframeImagen frame4 = new JframeImagen(p);
        //Histogramas h4 = new Histogramas(p);
        //h4.graficarHistogramas();
        
        //Binarización
        //Histogramas h = new Histogramas(imagen);
        //h.graficarHistogramas();
        //JframeImagen frame = new JframeImagen(imagen);
        //Image Bin = FiltrosEspaciales.Binarizar(imagen, 50);
        //JFrame frameB = new JframeImagen(Bin);
        //Histogramas h2 = new Histogramas(Bin);
        //h2.graficarHistogramas();
        
    


        //JFrameContraste frame = new JFrameContraste("Contraste", imagen);
        
        //JFrameIluminacion frame = new JFrameIluminacion("Iluminacion", imagen);
        //JFrameSegmentacion frame = new JFrameSegmentacion("Segmentacion",imagen);
        //Grises
        //Image imagenGrises = FiltrosEspaciales.generarImagenGirs(imagen);
        //JframeImagen frame2 = new JframeImagen(imagenGrises);
        //Negativa
        //Image imagenN = FiltrosEspaciales.generarImagenNegativa(imagen);
        //JframeImagen frame3 = new JframeImagen(imagenN);
        //Iluminada 
        //Image imagenIl = FiltrosEspaciales.iluminacion(imagen,60);
        //JframeImagen frame4 = new JframeImagen(imagenIl);
        //oscura
        //Image imagenS = FiltrosEspaciales.segmentarImagen(imagen, 100);
        //JframeImagen frame7 = new JframeImagen(imagenS);
        //Temperatura Calida
        //Image imagenTem = FiltrosEspaciales.temperatura(imagen,80);
        //JframeImagen frame5 = new JframeImagen(imagenTem);
        //Temperatura fría
        //Image imagenTem2 = FiltrosEspaciales.temperatura(imagen, -80);
        //JframeImagen frame6 = new JframeImagen(imagenTem2);
        //Contraste
        //Image imagenC = FiltrosEspaciales.contraste(imagen, 14);
        //JframeImagen frame4 = new JframeImagen(imagenC);
        //Histograma, imagen original
        //Histogramas hi = new Histogramas(imagen);
        //hi.graficarHistogramaGrises();
        //hi.graficarHistogramaAzul();
        //hi.graficarHistogramaRojo();
        //hi.graficarHistogramaVerde();
        //hi.graficarHistogramas();
        
        //Histogramas hi2 = new Histogramas(imagenIl);
        //hi2.graficarHistogramas();
        //Histogramas hi5 = new Histogramas(imagen);
        //hi5.graficarHistogramas();
        //Histogramas hi3 = new Histogramas(imagenTem);
        //hi3.graficarHistogramas();
        //Histogramas hi4 = new Histogramas(imagenTem2);
        //hi4.graficarHistogramas();
       
        System.out.println();

    }

    private static int[][] convertIntegers(ArrayList<Integer[]> lista) {
        int[][] ret = new int[lista.size()][lista.get(0).length];
        for(int i=0; i<ret.length;i++){
            ret[i] = toPrimitive(lista.get(i));
        }
        return ret;
    }

    private static int[] toPrimitive(Integer[] get) {
        int[] result = new int[get.length];
        for(int i=0; i<get.length;i++){
            result[i] = get[i].intValue();
        }
        return result;
    }
}