/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.sleepycat.db.internal;

import com.sleepycat.db.*;
import java.util.Comparator;

public class Db {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected Db(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Db obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  /* package */ synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        throw new UnsupportedOperationException("C++ destructor does not have public access");
      }
      swigCPtr = 0;
    }
  }

	/* package */ static final int GIGABYTE = 1 << 30;
	/*
	 * Internally, the JNI layer creates a global reference to each Db,
	 * which can potentially be different to this.  We keep a copy here so
	 * we can clean up after destructors.
	 */
	private long db_ref;
	private DbEnv dbenv;
	private boolean private_dbenv;

	public Database wrapper;
	private RecordNumberAppender append_recno_handler;
	private Comparator bt_compare_handler;
	private BtreeCompressor bt_compress_handler;
	private BtreeCompressor bt_decompress_handler;
	private BtreePrefixCalculator bt_prefix_handler;
	private Comparator dup_compare_handler;
	private FeedbackHandler db_feedback_handler;
	private Comparator h_compare_handler;
	private Hasher h_hash_handler;
	private PartitionHandler partition_handler;
	private SecondaryKeyCreator seckey_create_handler;
	private SecondaryMultiKeyCreator secmultikey_create_handler;
	private ForeignKeyNullifier foreignkey_nullify_handler;
	private ForeignMultiKeyNullifier foreignmultikey_nullify_handler;

	/* Called by the Db constructor */
	private void initialize(DbEnv dbenv) {
		if (dbenv == null) {
			private_dbenv = true;
			dbenv = db_java.getDbEnv0(this);
			dbenv.initialize();
		}
		this.dbenv = dbenv;
		db_ref = db_java.initDbRef0(this, this);
	}

	private void cleanup() {
		swigCPtr = 0;
		db_java.deleteRef0(db_ref);
		db_ref = 0L;
		if (private_dbenv)
			dbenv.cleanup();
		dbenv = null;
	}

	public boolean getPrivateDbEnv() throws com.sleepycat.db.DatabaseException {
		return private_dbenv;
	}

	public synchronized void close(int flags) throws DatabaseException {
		try {
			close0(flags);
		} finally {
			cleanup();
		}
	}

	public DbEnv get_env() throws DatabaseException {
		return dbenv;
	}

	private final void handle_append_recno(DatabaseEntry data, int recno)
	    throws DatabaseException {
		append_recno_handler.appendRecordNumber(wrapper, data, recno);
	}

	public RecordNumberAppender get_append_recno() throws com.sleepycat.db.DatabaseException {
		return append_recno_handler;
	}

	private final int handle_bt_compare(byte[] arr1, byte[] arr2) {
		return bt_compare_handler.compare(arr1, arr2);
	}

	private final int handle_bt_compress(DatabaseEntry dbt1,
	    DatabaseEntry dbt2, DatabaseEntry dbt3, DatabaseEntry dbt4,
	    DatabaseEntry dbt5) {
		return bt_compress_handler.compress(wrapper, dbt1, dbt2,
		    dbt3, dbt4, dbt5) ? 0 : DbConstants.DB_BUFFER_SMALL;
	}

	private final int handle_bt_decompress(DatabaseEntry dbt1,
	    DatabaseEntry dbt2, DatabaseEntry dbt3, DatabaseEntry dbt4,
	    DatabaseEntry dbt5) {
		return bt_compress_handler.decompress(wrapper, dbt1, dbt2,
		    dbt3, dbt4, dbt5) ? 0 : DbConstants.DB_BUFFER_SMALL;
	}

	public Comparator get_bt_compare() throws com.sleepycat.db.DatabaseException {
		return bt_compare_handler;
	}

	public BtreeCompressor get_bt_compress() throws com.sleepycat.db.DatabaseException {
		return bt_compress_handler;
	}

	public BtreeCompressor get_bt_decompress() throws com.sleepycat.db.DatabaseException {
		return bt_decompress_handler;
	}

	private final int handle_bt_prefix(DatabaseEntry dbt1,
					   DatabaseEntry dbt2) {
		return bt_prefix_handler.prefix(wrapper, dbt1, dbt2);
	}

	public BtreePrefixCalculator get_bt_prefix() throws com.sleepycat.db.DatabaseException {
		return bt_prefix_handler;
	}

	private final void handle_db_feedback(int opcode, int percent) {
		if (opcode == DbConstants.DB_UPGRADE)
			db_feedback_handler.upgradeFeedback(wrapper, percent);
		else if (opcode == DbConstants.DB_VERIFY)
			db_feedback_handler.upgradeFeedback(wrapper, percent);
		/* No other database feedback types known. */
	}

	public FeedbackHandler get_feedback() throws com.sleepycat.db.DatabaseException {
		return db_feedback_handler;
	}

	private final int handle_h_compare(byte[] arr1, byte[] arr2) {
		return h_compare_handler.compare(arr1, arr2);
	}

	public Comparator get_h_compare() throws com.sleepycat.db.DatabaseException {
		return h_compare_handler;
	}

	private final int handle_dup_compare(byte[] arr1, byte[] arr2) {
		return dup_compare_handler.compare(arr1, arr2);
	}

	public Comparator get_dup_compare() throws com.sleepycat.db.DatabaseException {
		return dup_compare_handler;
	}

	private final int handle_h_hash(byte[] data, int len) {
		return h_hash_handler.hash(wrapper, data, len);
	}

	public Hasher get_h_hash() throws com.sleepycat.db.DatabaseException {
		return h_hash_handler;
	}

	private final boolean handle_foreignkey_nullify(
					       DatabaseEntry key,	
					       DatabaseEntry data,	
					       DatabaseEntry seckey)
	    throws DatabaseException {
		if (foreignmultikey_nullify_handler != null)
			return foreignmultikey_nullify_handler.nullifyForeignKey(
			    (SecondaryDatabase)wrapper, key, data, seckey);
		else
			return foreignkey_nullify_handler.nullifyForeignKey(
			    (SecondaryDatabase)wrapper, data);
	}

	private final DatabaseEntry[] handle_seckey_create(
					       DatabaseEntry key,
					       DatabaseEntry data)
	    throws DatabaseException {

		if (secmultikey_create_handler != null) {
			java.util.HashSet keySet = new java.util.HashSet();
			secmultikey_create_handler.createSecondaryKeys(
			    (SecondaryDatabase)wrapper, key, data, keySet);
			if (!keySet.isEmpty())
				return (DatabaseEntry[])keySet.toArray(
				    new DatabaseEntry[keySet.size()]);
		} else {
			DatabaseEntry result = new DatabaseEntry();
			if (seckey_create_handler.createSecondaryKey(
			    (SecondaryDatabase)wrapper, key, data, result)) {
				DatabaseEntry[] results = { result };
				return results;
			}
		}

		return null;
	}

	public SecondaryKeyCreator get_seckey_create() throws com.sleepycat.db.DatabaseException {
		return seckey_create_handler;
	}

	public SecondaryMultiKeyCreator get_secmultikey_create() throws com.sleepycat.db.DatabaseException {
		return secmultikey_create_handler;
	}

	public void set_secmultikey_create(
	    SecondaryMultiKeyCreator secmultikey_create_handler) {
		this.secmultikey_create_handler = secmultikey_create_handler;
	}

	public void set_foreignmultikey_nullifier(ForeignMultiKeyNullifier nullify){
		this.foreignmultikey_nullify_handler = nullify;
	}

	private final int handle_partition(DatabaseEntry dbt1) {
		return partition_handler.partition(wrapper, dbt1);
	}

	public PartitionHandler get_partition_callback() throws com.sleepycat.db.DatabaseException {
		return partition_handler;
	}

	public synchronized void remove(String file, String database, int flags)
	    throws DatabaseException, java.io.FileNotFoundException {
		try {
			remove0(file, database, flags);
		} finally {
			cleanup();
		}
	}

	public synchronized void rename(String file, String database,
	    String newname, int flags)
	    throws DatabaseException, java.io.FileNotFoundException {
		try {
			rename0(file, database, newname, flags);
		} finally {
			cleanup();
		}
	}

	public synchronized boolean verify(String file, String database,
	    java.io.OutputStream outfile, int flags)
	    throws DatabaseException, java.io.FileNotFoundException {
		try {
			return verify0(file, database, outfile, flags);
		} finally {
			cleanup();
		}
	}

	public ErrorHandler get_errcall() /* no exception */ {
		return dbenv.get_errcall();
	}

	public void set_errcall(ErrorHandler db_errcall_fcn) /* no exception */ {
		dbenv.set_errcall(db_errcall_fcn);
	}

	public java.io.OutputStream get_error_stream() /* no exception */ {
		return dbenv.get_error_stream();
	}

	public void set_error_stream(java.io.OutputStream stream) /* no exception */ {
		dbenv.set_error_stream(stream);
	}

	public void set_errpfx(String errpfx) /* no exception */ {
		dbenv.set_errpfx(errpfx);
	}

	public String get_errpfx() /* no exception */ {
		return dbenv.get_errpfx();
	}

	public java.io.OutputStream get_message_stream() /* no exception */ {
		return dbenv.get_message_stream();
	}

	public void set_message_stream(java.io.OutputStream stream) /* no exception */ {
		dbenv.set_message_stream(stream);
	}

	public MessageHandler get_msgcall() /* no exception */ {
		return dbenv.get_msgcall();
	}

	public void set_msgcall(MessageHandler db_msgcall_fcn) /* no exception */ {
		dbenv.set_msgcall(db_msgcall_fcn);
	}

	public void set_paniccall(PanicHandler db_panic_fcn)
	    throws DatabaseException {
		dbenv.set_paniccall(db_panic_fcn);
	}

	public PanicHandler get_paniccall() throws com.sleepycat.db.DatabaseException {
		return dbenv.get_paniccall();
	}

  public Db(DbEnv dbenv, int flags) throws com.sleepycat.db.DatabaseException {
    this(db_javaJNI.new_Db(DbEnv.getCPtr(dbenv), dbenv, flags), true);
    initialize(dbenv);
  }

  public void associate(DbTxn txnid, Db secondary, com.sleepycat.db.SecondaryKeyCreator callback, int flags) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_associate(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, Db.getCPtr(secondary), secondary,  (secondary.seckey_create_handler = callback) != null ||
	(secondary.secmultikey_create_handler != null) , flags); }

  public void associate_foreign(Db primary, com.sleepycat.db.ForeignKeyNullifier callback, int flags) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_associate_foreign(swigCPtr, this, Db.getCPtr(primary), primary,  (primary.foreignkey_nullify_handler = callback) != null ||
	(primary.foreignmultikey_nullify_handler != null) , flags); }

  public void compact(DbTxn txnid, com.sleepycat.db.DatabaseEntry start, com.sleepycat.db.DatabaseEntry stop, com.sleepycat.db.CompactStats c_data, int flags, com.sleepycat.db.DatabaseEntry end) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_compact(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, start, stop, c_data, flags, end); }

  /* package */ int close0(int flags) {
    return db_javaJNI.Db_close0(swigCPtr, this, flags);
  }

  public Dbc cursor(DbTxn txnid, int flags) throws com.sleepycat.db.DatabaseException {
    long cPtr = db_javaJNI.Db_cursor(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, flags);
    return (cPtr == 0) ? null : new Dbc(cPtr, false);
  }

  public int del(DbTxn txnid, com.sleepycat.db.DatabaseEntry key, int flags) throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_del(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, key, flags);
  }

  public void err(int ret, String message) /* no exception */ {
    db_javaJNI.Db_err(swigCPtr, this, ret, message);
  }

  public void errx(String message) /* no exception */ {
    db_javaJNI.Db_errx(swigCPtr, this, message);
  }

  public int exists(DbTxn txnid, com.sleepycat.db.DatabaseEntry key, int flags) throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_exists(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, key, flags);
  }

  public int get(DbTxn txnid, com.sleepycat.db.DatabaseEntry key, com.sleepycat.db.DatabaseEntry data, int flags) throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, key, data, flags);
  }

  public int get_assoc_flags() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_assoc_flags(swigCPtr, this); }

  public boolean get_byteswapped() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_byteswapped(swigCPtr, this); }

  public long get_cachesize() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_cachesize(swigCPtr, this);
  }

  public int get_cachesize_ncache() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_cachesize_ncache(swigCPtr, this); }

  public String get_create_dir() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_create_dir(swigCPtr, this);
  }

  public String get_filename() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_filename(swigCPtr, this);
  }

  public String get_dbname() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_dbname(swigCPtr, this);
  }

  public int get_encrypt_flags() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_encrypt_flags(swigCPtr, this); }

  public int get_flags() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_flags(swigCPtr, this); }

  public int get_lorder() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_lorder(swigCPtr, this);
  }

  public DbMpoolFile get_mpf() throws com.sleepycat.db.DatabaseException {
    long cPtr = db_javaJNI.Db_get_mpf(swigCPtr, this);
    return (cPtr == 0) ? null : new DbMpoolFile(cPtr, false);
  }

  public int get_open_flags() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_open_flags(swigCPtr, this); }

  public int get_pagesize() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_pagesize(swigCPtr, this); }

  public int get_bt_minkey() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_bt_minkey(swigCPtr, this); }

  public int get_h_ffactor() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_h_ffactor(swigCPtr, this); }

  public int get_h_nelem() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_h_nelem(swigCPtr, this); }

  public int get_re_delim() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_re_delim(swigCPtr, this);
  }

  public int get_priority() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_priority(swigCPtr, this);
  }

  public String[] get_partition_dirs() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_partition_dirs(swigCPtr, this); }

  public com.sleepycat.db.DatabaseEntry get_partition_keys() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_partition_keys(swigCPtr, this); }

  public int get_partition_parts() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_partition_parts(swigCPtr, this);
  }

  public int get_re_len() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_re_len(swigCPtr, this); }

  public int get_re_pad() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_re_pad(swigCPtr, this);
  }

  public String get_re_source() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_re_source(swigCPtr, this);
  }

  public int get_q_extentsize() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_q_extentsize(swigCPtr, this); }

  public boolean get_multiple() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_multiple(swigCPtr, this); }

  public boolean get_transactional() throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_get_transactional(swigCPtr, this); }

  public int get_type() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_get_type(swigCPtr, this);
  }

  public Dbc join(Dbc[] curslist, int flags) throws com.sleepycat.db.DatabaseException {
    long cPtr = db_javaJNI.Db_join(swigCPtr, this, curslist, flags);
    return (cPtr == 0) ? null : new Dbc(cPtr, true);
  }

  public void key_range(DbTxn txnid, com.sleepycat.db.DatabaseEntry key, com.sleepycat.db.KeyRange key_range, int flags) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_key_range(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, key, key_range, flags); }

  public void open(DbTxn txnid, String file, String database, int type, int flags, int mode) throws com.sleepycat.db.DatabaseException, java.io.FileNotFoundException { db_javaJNI.Db_open(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, file, database, type, flags, mode); }

  public int pget(DbTxn txnid, com.sleepycat.db.DatabaseEntry key, com.sleepycat.db.DatabaseEntry pkey, com.sleepycat.db.DatabaseEntry data, int flags) throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_pget(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, key, pkey, data, flags);
  }

  public int put(DbTxn txnid, com.sleepycat.db.DatabaseEntry key, com.sleepycat.db.DatabaseEntry db_put_data, int flags) throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_put(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, key, db_put_data, flags);
  }

  /* package */ void remove0(String file, String database, int flags) { db_javaJNI.Db_remove0(swigCPtr, this, file, database, flags); }

  /* package */ void rename0(String file, String database, String newname, int flags) { db_javaJNI.Db_rename0(swigCPtr, this, file, database, newname, flags); }

  public void set_append_recno(com.sleepycat.db.RecordNumberAppender db_append_recno_fcn) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_append_recno(swigCPtr, this,  (append_recno_handler = db_append_recno_fcn) != null ); }

  public void set_bt_compare(java.util.Comparator bt_compare_fcn) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_bt_compare(swigCPtr, this,  (bt_compare_handler = bt_compare_fcn) != null ); }

  public void set_bt_minkey(int bt_minkey) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_bt_minkey(swigCPtr, this, bt_minkey); }

  public void set_bt_compress(com.sleepycat.db.BtreeCompressor bt_compress_fcn, com.sleepycat.db.BtreeCompressor bt_decompress_fcn) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_bt_compress(swigCPtr, this,  (bt_compress_handler = bt_compress_fcn) != null ,  (bt_decompress_handler = bt_decompress_fcn) != null ); }

  public void set_bt_prefix(com.sleepycat.db.BtreePrefixCalculator bt_prefix_fcn) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_bt_prefix(swigCPtr, this,  (bt_prefix_handler = bt_prefix_fcn) != null ); }

  public void set_cachesize(long bytes, int ncache) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_cachesize(swigCPtr, this, bytes, ncache); }

  public void set_create_dir(String dir) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_create_dir(swigCPtr, this, dir); }

  public void set_dup_compare(java.util.Comparator dup_compare_fcn) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_dup_compare(swigCPtr, this,  (dup_compare_handler = dup_compare_fcn) != null ); }

  public void set_encrypt(String passwd, int flags) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_encrypt(swigCPtr, this, passwd, flags); }

  public void set_feedback(com.sleepycat.db.FeedbackHandler db_feedback_fcn) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_feedback(swigCPtr, this,  (db_feedback_handler = db_feedback_fcn) != null ); }

  public void set_flags(int flags) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_flags(swigCPtr, this, flags); }

  public void set_h_compare(java.util.Comparator h_compare_fcn) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_h_compare(swigCPtr, this,  (h_compare_handler = h_compare_fcn) != null ); }

  public void set_h_ffactor(int h_ffactor) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_h_ffactor(swigCPtr, this, h_ffactor); }

  public void set_h_hash(com.sleepycat.db.Hasher h_hash_fcn) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_h_hash(swigCPtr, this,  (h_hash_handler = h_hash_fcn) != null ); }

  public void set_h_nelem(int h_nelem) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_h_nelem(swigCPtr, this, h_nelem); }

  public void set_lorder(int lorder) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_lorder(swigCPtr, this, lorder); }

  public void set_pagesize(long pagesize) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_pagesize(swigCPtr, this, pagesize); }

  public void set_partition(int parts, com.sleepycat.db.DatabaseEntry keys, com.sleepycat.db.PartitionHandler db_partition_fcn) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_partition(swigCPtr, this, parts, keys,  (partition_handler = db_partition_fcn) != null ); }

  public void set_partition_dirs(String[] dirp) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_partition_dirs(swigCPtr, this, dirp); }

  public void set_priority(int priority) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_priority(swigCPtr, this, priority); }

  public void set_re_delim(int re_delim) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_re_delim(swigCPtr, this, re_delim); }

  public void set_re_len(int re_len) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_re_len(swigCPtr, this, re_len); }

  public void set_re_pad(int re_pad) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_re_pad(swigCPtr, this, re_pad); }

  public void set_re_source(String source) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_re_source(swigCPtr, this, source); }

  public void set_q_extentsize(int extentsize) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_set_q_extentsize(swigCPtr, this, extentsize); }

  public void sort_multiple(com.sleepycat.db.DatabaseEntry key, com.sleepycat.db.DatabaseEntry data) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_sort_multiple(swigCPtr, this, key, data); }

  public Object stat(DbTxn txnid, int flags) throws com.sleepycat.db.DatabaseException { return db_javaJNI.Db_stat(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, flags); }

  public void sync(int flags) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_sync(swigCPtr, this, flags); }

  public int truncate(DbTxn txnid, int flags) throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.Db_truncate(swigCPtr, this, DbTxn.getCPtr(txnid), txnid, flags);
  }

  public void upgrade(String file, int flags) throws com.sleepycat.db.DatabaseException { db_javaJNI.Db_upgrade(swigCPtr, this, file, flags); }

  /* package */ boolean verify0(String file, String database, java.io.OutputStream outfile, int flags) { return db_javaJNI.Db_verify0(swigCPtr, this, file, database, outfile, flags); }

}
