import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    private static JsonParser jsonParser;

    public static void main(String[] args) throws Exception {

        // fazer uma conexao HTTP e buscar o top 250 filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();

        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=8r6pGnocBF9cgHMlHsciul5Xp4eFRqDz8HbF036M&start_date=2023-07-07&end_date=2023-07-10";
        // ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 4; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
