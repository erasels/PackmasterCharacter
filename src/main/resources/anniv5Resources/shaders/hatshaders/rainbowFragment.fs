// HSV to RBG from https://www.rapidtables.com/convert/color/hsv-to-rgb.html
// Rotation matrix from https://en.wikipedia.org/wiki/Rotation_matrix

const float PI = 3.1415926535;

varying vec2 v_texCoord;

uniform float u_strength;
uniform float u_speed;
uniform float u_angle;
uniform float u_time;
uniform float u_width;
uniform sampler2D u_texture;
uniform vec2 u_screenSize;

void main() {
	float hue = v_texCoord.x * cos(radians(u_angle)) * u_width - v_texCoord.y * sin(radians(u_angle)) * u_width;
	float hueR = hue + (5./7.)*u_time;
	float hueG = hue + (17./9.) * u_time;
	float hueB = hue;
	vec4 color = texture(u_texture, v_texCoord);
	vec3 red = vec3(color.r, 0, 0);
	vec3 green = vec3(0, color.g, 0);
	vec3 blue = vec3(0, 0, color.b);
	const vec3 k = vec3(0.57735, 0.57735, 0.57735);
    float cosAngleR = cos(hueR);
    red = vec3(red * cosAngleR + cross(k, red) * sin(hueR) + k * dot(k, red) * (1.0 - cosAngleR));
    float cosAngleG = cos(hueG);
    green = vec3(green * cosAngleG + cross(k, green) * sin(hueG) + k * dot(k, green) * (1.0 - cosAngleG));
    float cosAngleB = cos(hueB);
    blue = vec3(blue * cosAngleB + cross(k, blue) * sin(hueB) + k * dot(k, blue) * (1.0 - cosAngleB));
    vec3 newColor = red + green + blue;
    float originalBrightness = length(vec3(color.r, color.g, color.b));
    float newBrightness = length(newColor);
    if (newBrightness != 0) {
        newColor *= originalBrightness/newBrightness;
    }
	gl_FragColor = vec4(newColor, color.a);

}