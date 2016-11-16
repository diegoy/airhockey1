package com.pokanlab.airhockey1.android.programs;

import android.content.Context;

import com.pokanlab.airhockey1.R;

import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE1;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * Created by diego on 15/11/2016.
 */

public class TextureShaderProgram extends ShaderProgram {
    // Uniform locations;
    private final int uMatrixLocation;
    private final int uTextureUnitLocation;

    // Attribute locations
    private final int aPositionLocation;
    private final int aTextureCoordinatesLocation;
    private final int uTextureUnitLocation2;

    public TextureShaderProgram(Context context) {
        super(context, R.raw.texture_vertex_shader, R.raw.texture_fragment_shader);

        // Retrive uniform locations for the shader program.
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
        uTextureUnitLocation = glGetUniformLocation(program, U_TEXTURE_UNIT);
        uTextureUnitLocation2 = glGetUniformLocation(program, "u_TextureUnit2");

        // Retrive attribute locations for the shader program.
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aTextureCoordinatesLocation = glGetAttribLocation(program, A_TEXTURE_COORDINATES);
    }

    public void setUniforms(float[] matrix, int textureId, int textureId2) {
        //Pass the matrix into the shader program.
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

        //Set the active texture unit to texture unit 0
        glActiveTexture(GL_TEXTURE0);

        //Bind the texture to this unit
        glBindTexture(GL_TEXTURE_2D, textureId);

        //Tell the texture uniform sampler to use this texture in the shader
        // by telling it to read from texture unit 0.
        glUniform1i(uTextureUnitLocation, 0);

        //Set the active texture unit to texture unit 0
        glActiveTexture(GL_TEXTURE1);

        //Bind the texture to this unit
        glBindTexture(GL_TEXTURE_2D, textureId2);

        //Tell the texture uniform sampler to use this texture in the shader
        // by telling it to read from texture unit 1.
        glUniform1i(uTextureUnitLocation2, 1);
    }

    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }

    public int getTextureCoordinatesAttributeLocation() {
        return aTextureCoordinatesLocation;
    }
}
