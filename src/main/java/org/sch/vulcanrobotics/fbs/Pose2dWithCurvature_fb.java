// automatically generated by the FlatBuffers compiler, do not modify

package org.sch.vulcanrobotics.fbs;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Pose2dWithCurvature_fb extends Table {
  public static Pose2dWithCurvature_fb getRootAsPose2dWithCurvature_fb(ByteBuffer _bb) { return getRootAsPose2dWithCurvature_fb(_bb, new Pose2dWithCurvature_fb()); }
  public static Pose2dWithCurvature_fb getRootAsPose2dWithCurvature_fb(ByteBuffer _bb, Pose2dWithCurvature_fb obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public Pose2dWithCurvature_fb __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public Pose2d_fb pose() { return pose(new Pose2d_fb()); }
  public Pose2d_fb pose(Pose2d_fb obj) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }
  public double curvature() { int o = __offset(6); return o != 0 ? bb.getDouble(o + bb_pos) : 0.0; }

  public static int createPose2dWithCurvature_fb(FlatBufferBuilder builder,
      int poseOffset,
      double curvature) {
    builder.startObject(2);
    Pose2dWithCurvature_fb.addCurvature(builder, curvature);
    Pose2dWithCurvature_fb.addPose(builder, poseOffset);
    return Pose2dWithCurvature_fb.endPose2dWithCurvature_fb(builder);
  }

  public static void startPose2dWithCurvature_fb(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addPose(FlatBufferBuilder builder, int poseOffset) { builder.addOffset(0, poseOffset, 0); }
  public static void addCurvature(FlatBufferBuilder builder, double curvature) { builder.addDouble(1, curvature, 0.0); }
  public static int endPose2dWithCurvature_fb(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}
