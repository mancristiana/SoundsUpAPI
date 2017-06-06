package dk.kea.soundsup.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import dk.kea.soundsup.entities.Post;
import dk.kea.soundsup.entities.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.internal.util.reflection.Fields;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Andrei Atanasiu on 5/26/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestPostDAO
{
    @Mock
    DataSource mockDataSource;

    @Mock
    Connection mockConn;

    @Mock
    PreparedStatement mockPreparedStmnt;

    @Mock
    ResultSet mockResultSet;

    public TestPostDAO()
    {

    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp() throws SQLException
    {
        when(mockDataSource.getConnection()).thenReturn(mockConn);
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStmnt);
        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
        when(mockPreparedStmnt.execute()).thenReturn(Boolean.TRUE);
        when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(mockResultSet.getInt(Fields.GENERATED_KEYS)).thenReturn(postId);
    }

    @After
    public void tearDown()
    {
    }

    @Test(expected = SQLException.class)
    public void testCreateWithPreparedStmntException() throws SQLException
    {

        //mock
        when(mockConn.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException());


        try {
            PostDAO instance = new PostDAO(mockDataSource);
            instance.create(new Post());
        } catch (SQLException se) {
            //verify and assert
            verify(mockConn, times(1)).prepareStatement(anyString(), anyInt());
            verify(mockPreparedStmnt, times(0)).setString(anyInt(), anyString());
            verify(mockPreparedStmnt, times(0)).execute();
            verify(mockConn, times(0)).commit();
            verify(mockResultSet, times(0)).next();
            verify(mockResultSet, times(0)).getInt(Fields.GENERATED_KEYS);
            throw se;
        }

    }

}
