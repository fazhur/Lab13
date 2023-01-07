package scrapping;

import lombok.Setter;

public class ProxyReader implements Reader {
    private Reader reader;
    @Setter
    private String url;

    public ProxyReader(Reader reader) {
        this.reader = reader;
    }

    public String read() {
        if (reader == null) {
            return new PageReader(url).read();
        }
        return reader.read();
    }
}
