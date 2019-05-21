import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MowerTest {

    @Test
    public void should_initialize_mower_with_position(){
        // given
        Position initialPosition = Position.of(5,5);

        // when
        Mower mower = Mower.of(initialPosition);

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(initialPosition);
    }

}