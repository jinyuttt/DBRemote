package DBManager;

import DataStruct.DBResult;

public interface IDBSql {
    public DBResult ExecuteNonQuery(String sql);

    public DBResult ExecuteSclar(String sql);

    public SqlDataReader ExecuteReader(String sql);

    public DBResult ExecuteQuery(String sql);

    @SuppressWarnings("unchecked")
    public <T> DBResult ExecuteNonQuery(String sql, T... param);

    @SuppressWarnings("unchecked")
    public <T> DBResult ExecuteSclar(String sql, T... param);

    @SuppressWarnings("unchecked")
    public <T> SqlDataReader ExecuteReader(String sql, T... param);
    @SuppressWarnings("unchecked")
    public <T> DBResult ExecuteQuery(String sql, T... param);
}
