#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
//varying vec4 v_pos;
//varying vec4 v_apos;

uniform sampler2D u_texture;
uniform float u_scale;//settings dot scale
uniform vec2 u_screenSize;//width, height
uniform float x_time;

vec4 rgba(vec2 offset) {
    return v_color * texture2D(u_texture, v_texCoords + offset);
}

void main() {
    vec4 outputColor = rgba(vec2(0, 0));
    float magnitude = 0.4;
    float val = magnitude * v_texCoords.x + 1.0;
    float tmp = mod(x_time / 3.0, 1.5);
    val = val - tmp;
    float line_size = 0.075;
    if (outputColor.a > 0) {
        if (val - v_texCoords.y < (line_size / 3) && val - v_texCoords.y > -(line_size/3))
        {
            outputColor.r = outputColor.r + 0.1;
        }
        else if (val - v_texCoords.y < ((line_size/3)*2) && val - v_texCoords.y > -((line_size/3)*2))
        {
            outputColor.g = outputColor.g + 0.1;
        }
        else if (val - v_texCoords.y < line_size && val - v_texCoords.y > -line_size)
        {
            outputColor.b = outputColor.b + 0.1;
        }
    }
    gl_FragColor = vec4(outputColor.r, outputColor.g, outputColor.b, outputColor.a);
}