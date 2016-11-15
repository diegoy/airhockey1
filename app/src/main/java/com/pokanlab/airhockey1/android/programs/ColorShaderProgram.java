package com.pokanlab.airhockey1.android.programs;

import android.content.Context;
import android.opengl.GLES20;

import com.pokanlab.airhockey1.R;

/**
 * Created by diego on 15/11/2016.
 */
public class ColorShaderProgram extends ShaderProgram {
    // Uniform lcoations
    private final int uMatrixLocation;

    // Attribute locations
    private final int aPositionLocation;
    private final int aColorLocation;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader);
        //Retrieve uniform locations for the shader program;
        uMatrixLocation = GLES20.glGetUniformLocation(program, U_MATRIX);
        //Retrive attribue locations for the shader program.
        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(program, A_COLOR);
    }

    public void setUniforms(float[] matrix) {
        //Pass the matrix into the shader program.
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    }

    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }

    public int getColorAttributeLocation(){
        return aColorLocation;
    }
}
