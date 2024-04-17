package cr.ac.una.cashcardspringtdd.data;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@JsonTest
class CashCardJsonTest {
    @Autowired
    JacksonTester<CashCard> jsonTester;

    @Autowired
    private JacksonTester<CashCard[]> jsonList;

    private CashCard[] cashCards;

    @BeforeEach
    void setUp() {
        cashCards = Arrays.array(
                new CashCard(99L, 123.45),
                new CashCard(100L, 100.00),
                new CashCard(101L, 150.00));
    }

    @Test
    void cashCardSerialization() throws IOException {
        CashCard cashCard = new CashCard(99L, 225.45);
        assertThat(jsonTester.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(jsonTester.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(jsonTester.write(cashCard)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(jsonTester.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(jsonTester.write(cashCard)).extractingJsonPathNumberValue("@.amount").isEqualTo(225.45);
    }

    @Test
    void cashCardDeserialization() throws IOException {
        String expectedJson = """
                {
                    "id":99,
                    "amount":225.45
                }
                """;

        assertThat(jsonTester.parse(expectedJson)).isEqualTo(new CashCard(99L, 225.45));
        assertThat(jsonTester.parseObject(expectedJson).id()).isEqualTo(99);
        assertThat(jsonTester.parseObject(expectedJson).amount()).isEqualTo(225.45);
    }

    @Test
    void cashCardListSerialization() throws IOException {
        assertThat(jsonList.write(cashCards)).isStrictlyEqualToJson("arrayExpected.json");
    }
}