package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IpProcessorTest {

    @Test
    public void ProcessorTest() {
       IpProcessor processor = new IpProcessor();
       LogInfo info = processor.Process("\"ip1 - - [24/Apr/2011:04:06:01 -0400] \\\"GET /~strabal/grease/photo9/927-3.jpg HTTP/1.1\\\" 200 40028 \\\"-\\\" \\\"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\\\"\"");
       assertEquals(info.Length, 40028);
    }
}
