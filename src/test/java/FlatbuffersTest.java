import com.google.flatbuffers.FlatBufferBuilder;
import org.junit.Test;
import org.sch.vulcanrobotics.fbs.Pose2d_fb;

import static org.junit.Assert.assertTrue;

public class FlatbuffersTest {

  @Test
  public void flatbufferResearch() {
    System.out.println("Hello from FlatbuffersTest.flatbufferResearch!");
    FlatBufferBuilder fbBuilder = new FlatBufferBuilder(1024);
    int pt1 = Pose2d_fb.createPose2d_fb(fbBuilder, 20.0, -95.0,1.0, 0.0);
    int pt2 = Pose2d_fb.createPose2d_fb(fbBuilder, 150.0, -95.0, 1.0, 0.0);
    fbBuilder.finish(pt1);


    assertTrue("Should be true", true);
  }
}
