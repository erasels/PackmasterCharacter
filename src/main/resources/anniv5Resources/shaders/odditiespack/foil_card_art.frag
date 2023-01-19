#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float shift_amt;

vec4 rgba(vec2 offset) {
    return v_color * texture2D(u_texture, v_texCoords + offset);
}

vec4 shiftHue(vec3 col, float Shift)
{
    vec3 P = vec3(0.55735) * dot(vec3(0.55735), col);
    vec3 U = col - P;
    vec3 V = cross(vec3(0.55735), U);
    col = U * cos(Shift * 6.2832) + V * sin(Shift * 6.2832) + P;
    return vec4(col, 1.0);
}

void brightnessAdjust( inout vec4 color, in float b) {
    color.rgb += b;
}

void contrastAdjust( inout vec4 color, in float c) {
    float t = 0.5 - c * 0.5;
    color.rgb = color.rgb * c + t;
}

mat4 saturationMatrix( float saturation ) {
    vec3 luminance = vec3( 0.3086, 0.6094, 0.0820 );
    float oneMinusSat = 1.0 - saturation;
    vec3 red = vec3( luminance.x * oneMinusSat );
    red.r += saturation;

    vec3 green = vec3( luminance.y * oneMinusSat );
    green.g += saturation;

    vec3 blue = vec3( luminance.z * oneMinusSat );
    blue.b += saturation;

    return mat4(
    red,     0,
    green,   0,
    blue,    0,
    0, 0, 0, 1 );
}


void main() {
    vec4 outputColor = rgba(vec2(0, 0));

    outputColor = saturationMatrix(0.6) * outputColor;
    brightnessAdjust(outputColor, 0.125);
    contrastAdjust(outputColor, 1.1);
    vec4 hueShifted = shiftHue(outputColor.rgb, shift_amt);

    gl_FragColor = vec4(hueShifted.r, hueShifted.g, hueShifted.b, outputColor.a);
}