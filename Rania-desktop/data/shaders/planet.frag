#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform sampler2D u_texture2;
uniform vec2 v_speed;
uniform vec2 uvMin;
uniform vec2 uvMax;

void main() {
    vec4 planet = v_color * texture2D(u_texture, v_texCoords);
    vec2 len = (uvMax - uvMin);
    vec2 texCoordCloud = 2.0 * (v_texCoords - uvMin)/len - 1.0;

    float radius = dot(texCoordCloud, texCoordCloud);
    float f = (1.0 - sqrt(1.0 - radius))/(radius);
  	
  	texCoordCloud = texCoordCloud * f * len + uvMin + v_speed;
    
    vec4 cloud  = texture2D(u_texture2, texCoordCloud);
    planet.rgb = mix(planet.rgb, cloud.rgb, cloud.a);
    gl_FragColor = planet;
}