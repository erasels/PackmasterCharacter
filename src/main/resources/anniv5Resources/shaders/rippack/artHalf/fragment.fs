#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

uniform float u_y;

in vec2 v_texCoord;

varying LOWP vec4 v_color;

uniform sampler2D u_texture;

void main() {
	vec4 color = texture(u_texture, v_texCoord);
	vec4 transparentColor = vec4(1.0, 1.0, 1.0, 0.0);
	vec4 finalColor;
	if(v_texCoord.y > u_y) {
	    finalColor = mix(color, transparentColor, 0.7);
	} else {
	    finalColor = v_color * texture2D(u_texture, v_texCoord);
	}

	gl_FragColor = finalColor;
}
