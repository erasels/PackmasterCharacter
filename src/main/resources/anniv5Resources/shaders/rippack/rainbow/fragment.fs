// HSV to RBG from https://www.rapidtables.com/convert/color/hsv-to-rgb.html
// Rotation matrix from https://en.wikipedia.org/wiki/Rotation_matrix

const float PI = 3.1415926535;

varying vec2 v_texCoord;

uniform float u_time;
uniform sampler2D u_texture;
uniform vec2 u_screenSize;

void main() {
	float hue = v_texCoord.x * cos(radians(45.0f)) - v_texCoord.y * sin(radians(45.0f));
	hue = fract(hue + fract(u_time  * 1.5f));
	float x = 1. - abs(mod(hue / (1./ 6.), 2.) - 1.);
	vec3 rainbow;
	if(hue < 1./6.){
		rainbow = vec3(1., x, 0.);
	} else if (hue < 1./3.) {
		rainbow = vec3(x, 1., 0);
	} else if (hue < 0.5) {
		rainbow = vec3(0, 1., x);
	} else if (hue < 2./3.) {
		rainbow = vec3(0., x, 1.);
	} else if (hue < 5./6.) {
		rainbow = vec3(x, 0., 1.);
	} else {
		rainbow = vec3(1., 0., x);
	}
	vec4 color = texture(u_texture, v_texCoord);
	gl_FragColor = mix(color, vec4(rainbow, color.a), 1.0f);
}