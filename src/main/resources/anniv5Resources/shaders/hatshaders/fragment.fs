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
	hue = fract(hue + fract(u_time  * u_speed));
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
	float gray = dot(color, vec4(0.2126, 0.7152, 0.0722, 0));
	vec4 grayColor = vec4(gray,gray,gray,color.a);
	gl_FragColor = mix(color, vec4(rainbow, color.a), u_strength);

}