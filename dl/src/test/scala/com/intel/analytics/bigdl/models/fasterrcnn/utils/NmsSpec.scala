/*
 * Licensed to Intel Corporation under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * Intel Corporation licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.analytics.bigdl.models.fasterrcnn.utils

import com.intel.analytics.bigdl.tensor.{Storage, Tensor}
import org.scalatest.{FlatSpec, Matchers}

class NmsSpec extends FlatSpec with Matchers {
  val nmsTool = new Nms

  val dets = Tensor(Storage(Array(
    118.443, 153.083, 185.774, 200.722, 0.971,
    462.034, 264.274, 494.604, 330.577, 0.571,
    118.915, 152.607, 183.029, 201.831, 0.907,
    273.276, 313.298, 290.373, 332.000, 0.823,
    222.232, 102.474, 267.063, 157.714, 0.626,
    8.929, 220.077, 52.977, 274.980, 0.158,
    223.557, 110.244, 260.753, 199.844, 0.372,
    471.888, 263.361, 496.081, 330.052, 0.244,
    220.097, 106.501, 261.871, 181.278, 0.368,
    224.632, 106.336, 264.115, 204.238, 0.515,
    437.279, 275.709, 453.299, 331.414, 0.725,
    460.688, 265.795, 490.422, 326.198, 0.523,
    113.156, 153.182, 186.112, 204.249, 0.941,
    462.195, 264.010, 482.313, 319.434, 0.260,
    433.787, 273.001, 455.803, 328.885, 0.725,
    118.718, 149.466, 180.837, 201.148, 0.878,
    465.589, 267.575, 496.411, 330.804, 0.522,
    395.814, 266.073, 440.167, 320.397, 0.693,
    270.951, 311.826, 291.842, 332.000, 0.527,
    223.956, 111.038, 260.877, 205.891, 0.347,
    225.665, 105.183, 265.839, 200.012, 0.362,
    226.284, 107.343, 265.689, 177.230, 0.558,
    441.685, 269.949, 463.028, 327.326, 0.494,
    219.414, 110.355, 260.167, 205.106, 0.312,
    419.737, 270.352, 438.580, 314.481, 0.268,
    224.049, 117.312, 258.847, 207.168, 0.369,
    404.325, 269.633, 438.263, 319.910, 0.588,
    460.759, 267.950, 494.069, 325.949, 0.534,
    400.930, 270.101, 428.871, 310.200, 0.362,
    431.474, 270.428, 449.005, 328.687, 0.794,
    433.199, 271.865, 452.639, 328.357, 0.760,
    466.393, 263.735, 488.980, 329.780, 0.503,
    403.103, 268.001, 424.675, 307.339, 0.346,
    115.418, 154.071, 185.355, 204.464, 0.410,
    113.270, 152.525, 188.063, 203.212, 0.929,
    220.948, 108.912, 269.920, 155.635, 0.309,
    7.405, 220.837, 50.481, 271.846, 0.091,
    228.946, 106.412, 270.276, 183.366, 0.070,
    451.710, 268.922, 470.919, 325.454, 0.252,
    469.192, 264.243, 497.283, 330.949, 0.187,
    222.035, 106.477, 260.261, 206.672, 0.272,
    469.551, 263.659, 491.240, 318.009, 0.186,
    12.569, 221.949, 53.181, 274.147, 0.119,
    225.242, 105.573, 270.587, 201.162, 0.530,
    404.159, 270.073, 435.377, 314.044, 0.292,
    403.262, 276.099, 425.243, 311.837, 0.346,
    409.530, 267.877, 431.634, 309.738, 0.199,
    460.444, 268.105, 493.894, 331.400, 0.463,
    398.510, 267.975, 438.345, 317.406, 0.530,
    1.057, 161.920, 20.211, 235.907, 0.063,
    276.374, 312.630, 295.860, 332.000, 0.802,
    264.629, 313.349, 280.615, 332.000, 0.727,
    17.069, 225.382, 53.408, 270.590, 0.135,
    430.805, 274.467, 447.891, 325.076, 0.298,
    436.124, 271.792, 454.259, 331.702, 0.280,
    216.317, 120.285, 250.304, 203.728, 0.051,
    221.507, 109.100, 265.325, 200.629, 0.350,
    409.563, 274.314, 431.440, 316.641, 0.199,
    215.951, 110.210, 258.628, 207.847, 0.242,
    412.985, 269.994, 440.490, 323.173, 0.547,
    213.985, 115.856, 251.766, 203.897, 0.076,
    464.542, 264.489, 492.810, 326.315, 0.238,
    278.838, 313.821, 297.327, 332.000, 0.825,
    459.708, 271.676, 493.857, 329.325, 0.389,
    119.146, 152.652, 190.290, 201.858, 0.485,
    227.977, 115.149, 251.523, 158.620, 0.099,
    462.208, 266.932, 483.334, 303.763, 0.056,
    398.925, 269.121, 433.785, 320.155, 0.418,
    229.931, 108.734, 269.641, 207.364, 0.064,
    9.001, 221.614, 52.202, 268.987, 0.235,
    111.773, 147.647, 181.207, 200.310, 0.960,
    462.217, 271.700, 479.554, 325.380, 0.401,
    118.831, 150.663, 181.780, 203.724, 0.737,
    1.391, 167.173, 19.169, 224.301, 0.052,
    421.800, 270.635, 438.821, 291.770, 0.225,
    380.989, 268.706, 396.796, 296.849, 0.534,
    222.491, 111.636, 256.884, 207.242, 0.225,
    223.911, 107.550, 272.087, 159.442, 0.310,
    446.550, 274.379, 462.885, 323.894, 0.161,
    222.216, 108.852, 252.662, 177.608, 0.105,
    403.558, 268.638, 422.038, 292.002, 0.086,
    221.707, 108.304, 268.418, 184.089, 0.265,
    224.803, 104.941, 263.678, 145.068, 0.097,
    396.795, 269.594, 413.293, 291.388, 0.151,
    221.576, 105.371, 263.166, 158.501, 0.408,
    395.883, 269.561, 414.583, 303.999, 0.202,
    431.081, 274.415, 459.030, 330.070, 0.643,
    359.780, 315.825, 379.639, 332.000, 0.771,
    427.869, 273.060, 454.516, 330.629, 0.542,
    431.275, 281.951, 448.556, 328.006, 0.704,
    415.094, 271.724, 438.470, 318.723, 0.463,
    482.050, 257.346, 499.000, 332.000, 0.160,
    215.745, 117.824, 263.469, 211.728, 0.101,
    453.174, 272.965, 470.129, 320.082, 0.119,
    407.240, 271.363, 433.297, 310.490, 0.122,
    116.874, 151.891, 179.024, 204.943, 0.519,
    401.842, 268.359, 440.915, 325.935, 0.438,
    121.161, 151.844, 184.053, 203.238, 0.496,
    235.557, 109.980, 273.646, 180.226, 0.070,
    224.336, 106.283, 263.140, 206.927, 0.489,
    463.992, 264.308, 495.521, 321.720, 0.071,
    467.973, 267.598, 489.847, 327.825, 0.549,
    268.733, 315.200, 293.528, 332.000, 0.456,
    429.934, 269.373, 454.696, 330.941, 0.673,
    440.911, 268.384, 464.322, 331.362, 0.355,
    450.636, 272.877, 468.017, 324.722, 0.419,
    452.590, 266.954, 480.238, 326.914, 0.185,
    231.929, 108.454, 257.878, 154.010, 0.092,
    467.906, 266.680, 489.451, 304.785, 0.113,
    272.129, 310.907, 299.711, 332.000, 0.411,
    10.730, 223.756, 54.204, 272.484, 0.130,
    216.484, 112.347, 255.047, 200.992, 0.085).map(x => x.toFloat))).resize(112, 5)


  "nms with thresh 0.4" should "work properly" in {
    val expected = Array(1, 63, 30, 88, 52, 18, 5, 2, 76, 23, 26, 33, 25, 39, 70, 86, 92, 109, 50)
    val keep = nmsTool.nms(dets, 0.4f)
    keep should be(expected)
  }

  "nms with thresh 0.1" should "work properly" in {
    val expected = Array(1, 63, 30, 88, 52, 5, 2, 76, 68, 70, 79, 50)
    val keep = nmsTool.nms(dets, 0.1f)
    keep should be(expected)
  }
}
