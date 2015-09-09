import models.Bar;
import models.Foo;
import org.junit.Test;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.UnitTest;

public class FlushUnitTest extends UnitTest {

    @Test
    public void test() throws Throwable {
        final Long[] id = new Long[1];

        JPA.withinFilter(new F.Function0<Object>() {
            @Override
            public Object apply() throws Throwable {
                Foo foo = new Foo();
                foo.txt = "a";

                Bar bar = new Bar();
                bar.txt = "1";
                bar.foo = foo;

                foo.bars.add(bar);
                foo.save();

                assertEquals(0, foo.prePersistCalled.longValue());

                id[0] = foo.id;
                return null;
            }
        });

        JPA.withinFilter(new F.Function0<Object>() {
            @Override
            public Object apply() throws Throwable {
                Foo foo = Foo.findById(id[0]);
                foo.txt = "b";

                foo.bars.get(0).txt = "dirty";

                Bar barAdd = new Bar();
                barAdd.txt = "2";
                barAdd.foo = foo;
                foo.bars.add(barAdd);

                Foo.all().fetch(); // forces a flush when it should not
                assertEquals(0, foo.prePersistCalled.longValue());
                return null;
            }
        });
    }

}
