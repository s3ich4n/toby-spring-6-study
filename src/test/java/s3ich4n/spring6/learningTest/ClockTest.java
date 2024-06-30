package s3ich4n.spring6.learningTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockTest {
    // Clock을 이용하여 LocalDateTime.now 사용을 검증
    //      미세한 시간차이가 있을텐데, 그걸 테스트하라는 코드임
    @Test
    void Clock() {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        Assertions.assertThat(dt2).isAfter(dt1);
    }

    // Clock을 test에서 쓸 때, 원하는 시각으로 "현재시간" 조작이 되는지?
    @Test
    void FixedClock() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(1);

        Assertions.assertThat(dt1).isEqualTo(dt2);
        Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(1));
    }
}
