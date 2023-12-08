/*
 * Copyright (c) 2019-2023 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.bedrock.camera;

import org.geysermc.geyser.api.bedrock.camera.CameraFade;

public record GeyserCameraFade(
        int red,
        int green,
        int blue,
        float fadeInSeconds,
        float holdSeconds,
        float fadeOutSeconds
) implements CameraFade {

    public static class CustomFadeBuilder implements CameraFade.Builder {
        private int red;
        private int green;
        private int blue;

        private float fadeInSeconds;
        private float holdSeconds;
        private float fadeOutSeconds;

        @Override
        public CameraFade.Builder red(int red) {
            if (red < 0 || red > 255) {
                throw new IllegalArgumentException("Red must be between 0 and 255");
            }
            this.red = red;
            return this;
        }

        @Override
        public CameraFade.Builder green(int green) {
            if (green < 0 || green > 255) {
                throw new IllegalArgumentException("Green must be between 0 and 255");
            }
            this.green = green;
            return this;
        }

        @Override
        public CameraFade.Builder blue(int blue) {
            if (blue < 0 || blue > 255) {
                throw new IllegalArgumentException("Blue must be between 0 and 255");
            }
            this.blue = blue;
            return this;
        }

        @Override
        public CameraFade.Builder fadeInSeconds(float fadeInSeconds) {
            if (fadeInSeconds > 10f) {
                throw new IllegalArgumentException("Fade in seconds must be at most 10 seconds");
            }
            this.fadeInSeconds = fadeInSeconds;
            return this;
        }

        @Override
        public CameraFade.Builder holdSeconds(float holdSeconds) {
            if (holdSeconds > 10f) {
                throw new IllegalArgumentException("Hold seconds must be at most 10 seconds");
            }
            this.holdSeconds = holdSeconds;
            return this;
        }

        @Override
        public CameraFade.Builder fadeOutSeconds(float fadeOutSeconds) {
            if (fadeOutSeconds > 10f) {
                throw new IllegalArgumentException("Fade out seconds must be at most 10 seconds");
            }
            this.fadeOutSeconds = fadeOutSeconds;
            return this;
        }

        @Override
        public CameraFade build() {
            if (fadeInSeconds + holdSeconds + fadeOutSeconds < 0.5f) {
                throw new IllegalArgumentException("Fade times must be at least 0.5 seconds");
            }

            if (fadeInSeconds + holdSeconds + fadeOutSeconds > 10f) {
                throw new IllegalArgumentException("Fade times must be at most 10 seconds");
            }
            return new GeyserCameraFade(red, green, blue, fadeInSeconds, holdSeconds, fadeOutSeconds);
        }
    }
}
