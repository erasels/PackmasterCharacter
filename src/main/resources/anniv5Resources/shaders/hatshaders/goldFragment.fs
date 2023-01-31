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
	vec4 color = texture(u_texture, v_texCoord);
	float gray = dot(color, vec4(0.2126, 0.7152, 0.0722, 0));
	float enhancedGray = sin( gray * PI/2.);
	vec4 grayColor = vec4(gray,gray,gray,color.a);
	vec4 goldColor = grayColor * vec4(1.,0.95,0.,1.);


	gl_FragColor = goldColor;

}