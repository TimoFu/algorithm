# algorithm
| metadata type=sources index=auditd  | rename totalCount AS count | sort -count | table source,count
| tstats count FROM datamodel="Authentication" BY source | rex field=source "/?[^/]+?(/[^/]+)+/(?P<source>[^$]+)" | head 10000 | stats sum(count) by source